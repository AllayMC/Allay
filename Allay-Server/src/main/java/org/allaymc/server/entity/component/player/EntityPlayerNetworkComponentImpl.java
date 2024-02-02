package org.allaymc.server.entity.component.player;

import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.client.storage.PlayerData;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.event.PlayerLoggedInEvent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.registry.EntityTypeRegistry;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.recipe.RecipeRegistry;
import org.allaymc.api.item.registry.CreativeItemRegistry;
import org.allaymc.api.item.registry.ItemTypeRegistry;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.pack.PackRegistry;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.server.network.DataPacketProcessor;
import org.allaymc.server.network.DataPacketProcessorHolder;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.AuthoritativeMovementMode;
import org.cloudburstmc.protocol.bedrock.data.ChatRestrictionLevel;
import org.cloudburstmc.protocol.bedrock.data.GamePublishSetting;
import org.cloudburstmc.protocol.bedrock.data.SpawnBiomeType;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3fc;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static org.allaymc.api.utils.NbtUtils.readVector3f;
import static org.allaymc.api.utils.NbtUtils.writeVector3f;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerNetworkComponentImpl implements EntityPlayerNetworkComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_network_component");

    protected final AtomicBoolean initialized = new AtomicBoolean(false);
    protected final AtomicBoolean loggedIn = new AtomicBoolean(false);
    protected final AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(Server.SETTINGS.worldSettings().doFirstSpawnChunkThreshold());

    protected final Server server = Server.getInstance();
    protected final Queue<BedrockPacket> packetQueue;
    protected final DataPacketProcessorHolder dataPacketProcessorHolder;

    protected final BedrockPacketHandler loginPacketHandler = new AllayClientLoginPacketHandler();

    @Manager
    protected ComponentManager<EntityPlayer> manager;
    @Getter
    protected LoginData loginData;
    @Getter
    protected boolean networkEncryptionEnabled = false;
    @Getter
    protected SecretKey encryptionSecretKey;
    @ComponentedObject
    protected EntityPlayer player;
    @Dependency
    protected EntityPlayerBaseComponentImpl baseComponent;
    // It will be set while client disconnecting from server
    protected String disconnectReason;
    protected BedrockServerSession session;

    public EntityPlayerNetworkComponentImpl() {
        packetQueue = PlatformDependent.newSpscQueue();
        dataPacketProcessorHolder = new DataPacketProcessorHolder();
        DataPacketProcessorHolder.registerDefaultPacketProcessors(dataPacketProcessorHolder);
    }

    @Override
    public void handleDataPacket() {
        BedrockPacket pk;
        while ((pk = this.packetQueue.poll()) != null) {
            DataPacketProcessor<BedrockPacket> processor = this.dataPacketProcessorHolder.getProcessor(pk);
            if (processor == null) {
                log.warn("Received packet without a packet handler for {}: {}", session.getSocketAddress(), pk);
            } else {
                processor.handle(player, pk);
            }
        }
        handleDisconnect();
    }

    public void handleDisconnect() {
        // Before client disconnect, there may be other packets which are not handled
        // So we handle disconnect after we handled all other packets
        if (disconnectReason != null) {
            // Client is disconnected from server
            dataPacketProcessorHolder.getDisconnectProcessor().accept(player, disconnectReason);
            disconnectReason = null;
        }
    }

    @Override
    public boolean isInitialized() {
        return initialized.get();
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn.get();
    }

    @Override
    public BedrockServerSession getClientSession() {
        return session;
    }

    @Override
    public void setClientSession(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                if (loginPacketHandler.handlePacket(packet) == PacketSignal.HANDLED) return PacketSignal.HANDLED;
                packetQueue.add(packet);
                return PacketSignal.HANDLED;
            }

            @Override
            public void onDisconnect(String reason) {
                // Handle disconnect in world main thread
                if (baseComponent.isSpawned())
                    disconnectReason = reason;
                    // If the player is not spawned, we call Server::onDisconnect() directly
                    // As it won't cause any concurrent problem
                else server.onDisconnect(player, reason);
            }
        });
    }

    @Override
    public void onChunkInRangeSent() {
        if (doFirstSpawnChunkThreshold.get() > 0) {
            if (doFirstSpawnChunkThreshold.decrementAndGet() == 0) {
                doFirstSpawnPlayer();
            }
        }
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        session.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        session.sendPacketImmediately(packet);
    }

    @Override
    public void disconnect(@MayContainTrKey String reason, boolean hideReason) {
        session.disconnect(I18n.get().tr(reason), hideReason);
    }

    protected void doFirstSpawnPlayer() {
        // Load EntityPlayer's NBT
        player.loadNBT(server.getPlayerStorage().readPlayerData(player).getPlayerNBT());

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(player.getUniqueId());
        setEntityDataPacket.getMetadata().putAll(player.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(player.getWorld().getTick());
        sendPacket(setEntityDataPacket);

        // Update abilities, adventure settings, entity flags that are related to game type
        player.setGameType(player.getGameType());

        sendPacket(Server.getInstance().getCommandRegistry().encodeAvailableCommandsPacketFor(player));

        // PlayerListPacket can only be sent in this stage, otherwise the client won't show its skin
        server.addToPlayerList(player);
        if (server.getOnlinePlayerCount() > 1) {
            server.sendFullPlayerListInfoTo(player);
        }

        player.sendAttributesToClient();

        sendInventories();

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);

        player.getLocation().dimension().getWorld().viewTime(List.of(player));
        // Save player data the first time
        server.getPlayerStorage().savePlayerData(player);
    }

    private void sendInventories() {
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), FixedContainerId.OFFHAND);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), FixedContainerId.ARMOR);
        // No need to send cursor's content to client because there is nothing in cursor
    }

    private void initializePlayer() {
        // initializePlayer() method will read all the data in PlayerData except playerNBT
        // To be more exactly, we will validate and set player's current pos and spawn point in this method
        // And playerNBT will be used in EntityPlayer::loadNBT() in doFirstSpawnPlayer() method
        var playerData = server.getPlayerStorage().readPlayerData(player);
        // Validate and set player pos
        Dimension dimension;
        Vector3fc currentPos;
        var logOffWorld = server.getWorldPool().getWorld(playerData.getCurrentWorldName());
        if (logOffWorld == null) {
            // The world where player logged off doesn't exist
            dimension = server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());
            // The old pos stored in playerNBT is invalid, we should replace it with the new one!
            var builder = playerData.getPlayerNBT().toBuilder();
            writeVector3f(builder, "Pos", "x", "y", "z", currentPos);
            playerData.setPlayerNBT(builder.build());
        } else {
            dimension = logOffWorld.getDimension(playerData.getCurrentDimensionId());
            // Read current pos from playerNBT
            currentPos = readVector3f(playerData.getPlayerNBT(), "Pos", "x", "y", "z");
        }
        // Validate and set spawn point
        validateAndSetSpawnPoint(playerData);
        // Load the current point chunk firstly so that we can add player entity into the chunk
        dimension.getChunkService().getChunkImmediately(
                (int) currentPos.x() >> 4,
                (int) currentPos.z() >> 4
        );
        baseComponent.setLocation(new Location3f(currentPos.x(), currentPos.y(), currentPos.z(), dimension), false);
        dimension.addPlayer(player);

        var spawnWorld = dimension.getWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().addAll(spawnWorld.getWorldData().getGameRules().toNetworkGameRuleData());
        startGamePacket.setUniqueEntityId(player.getUniqueId());
        startGamePacket.setRuntimeEntityId(player.getUniqueId());
        startGamePacket.setPlayerGameType(player.getGameType());
        var loc = player.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        startGamePacket.setSeed(spawnWorld.getWorldData().getRandomSeed());
        startGamePacket.setDimensionId(dimension.getDimensionInfo().dimensionId());
        startGamePacket.setGeneratorId(dimension.getGenerator().getType().getId());
        startGamePacket.setLevelGameType(spawnWorld.getWorldData().getGameType());
        startGamePacket.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setLevelName(Server.SETTINGS.genericSettings().motd());
        startGamePacket.setLevelId("");
        startGamePacket.setDefaultPlayerPermission(Server.SETTINGS.genericSettings().defaultPermission());
        startGamePacket.setServerChunkTickRange(spawnWorld.getWorldData().getServerChunkTickRange());
        startGamePacket.setVanillaVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
        startGamePacket.setPremiumWorldTemplateId("");
        startGamePacket.setInventoriesServerAuthoritative(true);
        startGamePacket.setItemDefinitions(ItemTypeRegistry.getRegistry().getItemDefinitions());
        startGamePacket.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER);
        startGamePacket.setServerAuthoritativeBlockBreaking(true);
        startGamePacket.setCommandsEnabled(true);
        startGamePacket.setMultiplayerGame(true);
        startGamePacket.setBroadcastingToLan(true);
        startGamePacket.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        startGamePacket.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        startGamePacket.setServerEngine("Allay");
        startGamePacket.setBlockRegistryChecksum(0L);
        startGamePacket.setPlayerPropertyData(NbtMap.EMPTY);
        startGamePacket.setWorldTemplateId(new UUID(0, 0));
        startGamePacket.setWorldEditor(false);
        startGamePacket.setChatRestrictionLevel(ChatRestrictionLevel.NONE);
        startGamePacket.setSpawnBiomeType(SpawnBiomeType.DEFAULT);
        startGamePacket.setCustomBiomeName("");
        startGamePacket.setEducationProductionId("");
        startGamePacket.setForceExperimentalGameplay(OptionalBoolean.empty());
        startGamePacket.setBlockNetworkIdsHashed(true);
        sendPacket(startGamePacket);

        session.getPeer().getCodecHelper().setItemDefinitions(
                SimpleDefinitionRegistry
                        .<ItemDefinition>builder()
                        .addAll(startGamePacket.getItemDefinitions())
                        .build()
        );

        session.getPeer().getCodecHelper().setBlockDefinitions(
                SimpleDefinitionRegistry
                        .<BlockDefinition>builder()
                        .addAll(BlockTypeRegistry.getRegistry().getBlockDefinitions())
                        .build()
        );

        var availableEntityIdentifiersPacket = new AvailableEntityIdentifiersPacket();
        availableEntityIdentifiersPacket.setIdentifiers(EntityTypeRegistry.getRegistry().getAvailableEntityIdentifierTag());
        sendPacket(availableEntityIdentifiersPacket);

        var biomeDefinitionListPacket = new BiomeDefinitionListPacket();
        biomeDefinitionListPacket.setDefinitions(BiomeTypeRegistry.getRegistry().getBiomeDefinition());
        sendPacket(biomeDefinitionListPacket);

        var creativeContentPacket = new CreativeContentPacket();
        creativeContentPacket.setContents(CreativeItemRegistry.getRegistry().getNetworkItemDataArray());
        sendPacket(creativeContentPacket);

        var craftingDataPacket = RecipeRegistry.getRegistry().getCraftingDataPacket();
        sendPacket(craftingDataPacket);
    }

    protected void validateAndSetSpawnPoint(PlayerData playerData) {
        Location3ic spawnPoint;
        var spawnWorld = server.getWorldPool().getWorld(playerData.getSpawnPointWorldName());
        if (spawnWorld == null) {
            // 出生点所在的世界不存在
            // 使用全局出生点替代
            spawnPoint = server.getWorldPool().getGlobalSpawnPoint();
            playerData.setSpawnPoint(spawnPoint);
            playerData.setSpawnPointWorldName(spawnPoint.dimension().getWorld().getWorldData().getName());
            playerData.setSpawnPointDimensionId(spawnPoint.dimension().getDimensionInfo().dimensionId());
        } else {
            var vec = playerData.getSpawnPoint();
            spawnPoint = new Location3i(vec.x(), vec.y(), vec.z(), spawnWorld.getDimension(playerData.getSpawnPointDimensionId()));
        }
        player.setSpawnPoint(spawnPoint);
    }

    private class AllayClientLoginPacketHandler implements BedrockPacketHandler {

        public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

        @Override
        public PacketSignal handle(RequestNetworkSettingsPacket packet) {
            var protocolVersion = packet.getProtocolVersion();
            var supportedProtocolVersion = server.getNetworkServer().getCodec().getProtocolVersion();
            if (protocolVersion != supportedProtocolVersion) {
                var loginFailedPacket = new PlayStatusPacket();
                if (protocolVersion > supportedProtocolVersion) {
                    loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
                } else {
                    loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
                }
                session.sendPacketImmediately(loginFailedPacket);
                return PacketSignal.HANDLED;
            }
            var settingsPacket = new NetworkSettingsPacket();
            settingsPacket.setCompressionAlgorithm(Server.SETTINGS.networkSettings().compressionAlgorithm());
            settingsPacket.setCompressionThreshold(Server.SETTINGS.networkSettings().compressionThreshold());
            sendPacketImmediately(settingsPacket);
            session.setCompression(settingsPacket.getCompressionAlgorithm());
            session.setCompressionLevel(settingsPacket.getCompressionThreshold());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(LoginPacket packet) {
            loginData = LoginData.decode(packet);

            if (!loginData.isXboxAuthenticated() && Server.SETTINGS.networkSettings().xboxAuth()) {
                disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTAUTHENTICATED);
                return PacketSignal.HANDLED;
            }

            var name = loginData.getDisplayName();
            if (!NAME_PATTERN.matcher(name).matches()) {
                disconnect(TrKeys.M_DISCONNECTIONSCREEN_INVALIDNAME);
                return PacketSignal.HANDLED;
            }

            if (!loginData.getSkin().isValid()) {
                session.disconnect(TrKeys.M_DISCONNECTIONSCREEN_INVALIDSKIN);
                return PacketSignal.HANDLED;
            }

            var otherDevice = server.getOnlinePlayers().get(loginData.getUuid());
            if (otherDevice != null) {
                otherDevice.disconnect(TrKeys.M_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
            }

            if (!Server.SETTINGS.networkSettings().enableNetworkEncryption()) {
                completeLogin();
                return PacketSignal.HANDLED;
            }

            try {
                var clientKey = EncryptionUtils.parseKey(loginData.getIdentityPublicKey());
                var encryptionKeyPair = EncryptionUtils.createKeyPair();
                var encryptionToken = EncryptionUtils.generateRandomToken();
                encryptionSecretKey = EncryptionUtils.getSecretKey(
                        encryptionKeyPair.getPrivate(), clientKey,
                        encryptionToken
                );
                var encryptionJWT = EncryptionUtils.createHandshakeJwt(encryptionKeyPair, encryptionToken);
                networkEncryptionEnabled = true;
                var handshakePacket = new ServerToClientHandshakePacket();
                handshakePacket.setJwt(encryptionJWT);
                sendPacketImmediately(handshakePacket);
                session.enableEncryption(encryptionSecretKey);
                // completeLogin() when client send back ClientToServerHandshakePacket
            } catch (Exception exception) {
                log.warn("Failed to initialize encryption for client " + name, exception);
                disconnect("disconnectionScreen.internalError");
            }

            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ClientToServerHandshakePacket packet) {
            if (isNetworkEncryptionEnabled()) completeLogin();
            else log.warn("Client " + player.getOriginName() + " sent ClientToServerHandshakePacket without encryption enabled");
            return PacketSignal.HANDLED;
        }

        protected void completeLogin() {
            var playStatusPacket = new PlayStatusPacket();
            if (server.getOnlinePlayerCount() >= Server.SETTINGS.genericSettings().maxClientCount()) {
                playStatusPacket.setStatus(PlayStatusPacket.Status.FAILED_SERVER_FULL_SUB_CLIENT);
            } else {
                playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
            }
            sendPacket(playStatusPacket);
            loggedIn.set(true);
            server.onLoggedIn(player);
            // todo plugin event
            manager.callEvent(PlayerLoggedInEvent.INSTANCE);
            sendPacket(PackRegistry.getRegistry().getPacksInfoPacket());
        }

        @Override
        public PacketSignal handle(ResourcePackClientResponsePacket packet) {
            switch (packet.getStatus()) {
                case SEND_PACKS -> {
                    for (var packId : packet.getPackIds()) {
                        var pack = PackRegistry.getRegistry().get(UUID.fromString(packId.split("_")[0]));
                        if (pack == null) {
                            disconnect(TrKeys.M_DISCONNECTIONSCREEN_RESOURCEPACK);
                            return PacketSignal.HANDLED;
                        }

                        sendPacket(pack.toNetwork());
                    }
                }
                case HAVE_ALL_PACKS -> sendPacket(PackRegistry.getRegistry().getPackStackPacket());
                case COMPLETED -> initializePlayer();
                default -> disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOREASON);
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ResourcePackChunkRequestPacket packet) {
            var pack = PackRegistry.getRegistry().get(packet.getPackId());
            if (pack == null) {
                player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_RESOURCEPACK);
                return PacketSignal.HANDLED;
            }

            player.sendPacket(pack.getChunkDataPacket(packet.getChunkIndex()));
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SetLocalPlayerAsInitializedPacket packet) {
            // We only accept player's movement inputs which are after SetLocalPlayerAsInitializedPacket
            // So after player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with client
            // Otherwise the client will snap into the ground
            player.sendLocationToSelf();
            initialized.set(true);
            Server.getInstance().broadcastTr("§e%minecraft:multiplayer.player.joined", player.getOriginName());
            return PacketSignal.HANDLED;
        }
    }
}
