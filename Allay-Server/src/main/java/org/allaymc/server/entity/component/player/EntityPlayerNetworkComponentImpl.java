package org.allaymc.server.entity.component.player;

import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.client.data.AdventureSettings;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.container.FixedContainerId;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.attribute.Attribute;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.component.event.PlayerLoggedInEvent;
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
import org.allaymc.server.network.DataPacketProcessor;
import org.allaymc.server.network.DataPacketProcessorHolder;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.biome.BiomeTypeRegistry;
import org.allaymc.api.world.gamerule.GameRule;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3ic;

import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityPlayerNetworkComponentImpl implements EntityPlayerNetworkComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:player_network_component");
    @Manager
    protected ComponentManager<EntityPlayer> manager;
    @Getter
    protected LoginData loginData;
    @Getter
    protected boolean networkEncryptionEnabled = false;
    @Getter
    @Nullable
    protected SecretKey encryptionSecretKey;
    @ComponentedObject
    protected EntityPlayer player;
    protected final AtomicBoolean initialized = new AtomicBoolean(false);
    protected final AtomicBoolean loggedIn = new AtomicBoolean(false);
    protected final AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(Server.SETTINGS.worldSettings().doFirstSpawnChunkThreshold());
    protected final Server server = Server.getInstance();
    protected final Queue<BedrockPacket> packetQueue;
    protected final DataPacketProcessorHolder dataPacketProcessorHolder;
    protected final BedrockPacketHandler loginPacketHandler = new AllayClientLoginPacketHandler();
    protected BedrockServerSession session;

    public EntityPlayerNetworkComponentImpl() {
        packetQueue = PlatformDependent.newSpscQueue();
        dataPacketProcessorHolder = new DataPacketProcessorHolder();
        DataPacketProcessorHolder.registerDefaultPacketProcessors(dataPacketProcessorHolder);
    }

    @Override
    public void handleDataPacket() {
        if (!isInitialized()) return;
        BedrockPacket pk;
        while ((pk = this.packetQueue.poll()) != null) {
            DataPacketProcessor<BedrockPacket> processor = this.dataPacketProcessorHolder.getProcessor(pk);
            if (processor == null) {
                log.warn("Received packet without a packet handler for {}: {}", session.getSocketAddress(), pk);
            } else {
                processor.handle(player, pk);
            }
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
    public void setClientSession(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(new BedrockPacketHandler() {
            @Override
            public PacketSignal handlePacket(BedrockPacket packet) {
                if (loginPacketHandler.handlePacket(packet) == PacketSignal.HANDLED) {
                    return PacketSignal.HANDLED;
                }
                packetQueue.add(packet);
                return PacketSignal.HANDLED;//For our own log packet
            }

            @Override
            public void onDisconnect(String reason) {
                server.onDisconnect(player);
            }
        });
    }

    @Override
    public BedrockServerSession getClientSession() {
        return session;
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
        server.getPlayerStorage().readPlayerData(player);

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(player.getUniqueId());
        setEntityDataPacket.getMetadata().putAll(player.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(player.getWorld().getTick());
        sendPacket(setEntityDataPacket);

        var adventureSettings = player.getAdventureSettings();
        adventureSettings.set(AdventureSettings.Type.OPERATOR, player.isOp());
        adventureSettings.set(AdventureSettings.Type.TELEPORT, true);
        var gameType = player.getGameType();
        adventureSettings.set(AdventureSettings.Type.WORLD_IMMUTABLE, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.ALLOW_FLIGHT, gameType != GameType.SURVIVAL && gameType != GameType.ADVENTURE);
        adventureSettings.set(AdventureSettings.Type.NO_CLIP, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.FLYING, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.ATTACK_MOBS, gameType == GameType.SURVIVAL || gameType == GameType.CREATIVE);
        adventureSettings.set(AdventureSettings.Type.ATTACK_PLAYERS, gameType == GameType.SURVIVAL || gameType == GameType.CREATIVE);
        adventureSettings.set(AdventureSettings.Type.NO_PVM, gameType == GameType.SPECTATOR);
        adventureSettings.update();

        sendPacket(Server.getInstance().getCommandRegistry().encodeAvailableCommandsPacketFor(player));

        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(player.getUniqueId());
        for (Attribute attribute : player.getAttributes()) {
            updateAttributesPacket.getAttributes().add(attribute.toNetwork());
        }
        updateAttributesPacket.setTick(player.getWorld().getTick());
        sendPacket(updateAttributesPacket);
        // PlayerListPacket can only be sent in this stage, otherwise the client won't show its skin
        server.addToPlayerList(player);
        if (server.getOnlinePlayerCount() > 1) {
            server.sendFullPlayerListInfoTo(player);
        }

        sendInventories();

        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);

        player.getLocation().dimension().getWorld().viewTime(List.of(player));
    }

    private void sendInventories() {
        //TODO: setHolder
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), FixedContainerId.OFFHAND);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), FixedContainerId.ARMOR);
        //No need to send cursor's content to client because there is nothing in cursor
    }

    private void initializePlayer() {
        // TODO: save last pos for each player instead of using the global spawn point
        Vector3ic spawnPos = server.getDefaultWorld().getWorldData().getSpawnPoint();
        Dimension dimension = server.getDefaultWorld().getDimension(0);
        // Load the spawn point chunk first so that we can add player entity into the chunk
        dimension.getChunkService().getChunkImmediately(
                spawnPos.x() >> 4,
                spawnPos.z() >> 4
        );
        player.setLocationAndCheckChunk(new Location3f(spawnPos.x(), 100, spawnPos.z(), dimension));
        dimension.addPlayer(player);

        {
            //send BaseGamePacket
            var spawnWorld = server.getDefaultWorld();
            var startGamePacket = new StartGamePacket();
            startGamePacket.getGamerules().add(GameRule.SHOW_COORDINATES.toNetwork());
            startGamePacket.setUniqueEntityId(player.getUniqueId());
            startGamePacket.setRuntimeEntityId(player.getUniqueId());
            startGamePacket.setPlayerGameType(player.getGameType());
            var loc = player.getLocation();
            var worldSpawn = spawnWorld.getWorldData().getSpawnPoint(); //TODO: save spawn world per player
            startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
            startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
            startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
            startGamePacket.setSeed(spawnWorld.getWorldData().getRandomSeed());
            startGamePacket.setDimensionId(dimension.getDimensionInfo().dimensionId());
            startGamePacket.setGeneratorId(dimension.getGenerator().getType().getId());
            startGamePacket.setLevelGameType(spawnWorld.getWorldData().getGameType());
            startGamePacket.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
            startGamePacket.setTrustingPlayers(true);
            startGamePacket.setDayCycleStopTime(0);
            startGamePacket.setLevelName(Server.SETTINGS.genericSettings().motd());
            //TODO
            startGamePacket.setLevelId("");
            //TODO
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
            //TODO
            startGamePacket.setCurrentTick(0);
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

            if (server.getOnlinePlayers().containsKey(loginData.getUuid())) {
                disconnect(TrKeys.M_DISCONNECTIONSCREEN_LOGGEDINOTHERLOCATION);
                return PacketSignal.HANDLED;
            }

            if (!loginData.getSkin().isValid()) {
                session.disconnect(TrKeys.M_DISCONNECTIONSCREEN_INVALIDSKIN);
                return PacketSignal.HANDLED;
            }

            if (Server.SETTINGS.networkSettings().enableNetworkEncryption()) {
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
                    //completeLogin() when client send back ClientToServerHandshakePacket
                } catch (Exception exception) {
                    log.warn("Failed to initialize encryption for client " + name, exception);
                    disconnect("disconnectionScreen.internalError");
                }
            } else {
                completeLogin();
            }

            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ClientToServerHandshakePacket packet) {
            if (isNetworkEncryptionEnabled()) {
                completeLogin();
            } else
                log.warn("Client " + player.getOriginName() + " sent ClientToServerHandshakePacket without encryption enabled");
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
            //todo plugin event
            manager.callEvent(PlayerLoggedInEvent.INSTANCE);
            //TODO: Resource Packs
            sendPacket(new ResourcePacksInfoPacket());
        }

        @Override
        public PacketSignal handle(ResourcePackClientResponsePacket packet) {
            switch (packet.getStatus()) {
                case SEND_PACKS -> {
                    //TODO: RP load
                }
                case HAVE_ALL_PACKS -> {
                    var stackPacket = new ResourcePackStackPacket();
                    // Just left a '*' here, if we put in exact game version
                    // It is possible that client won't send back ResourcePackClientResponsePacket(packIds=[*], status=COMPLETED)
                    stackPacket.setGameVersion("*");
                    sendPacket(stackPacket);
                    // TODO: possible bug here
                }
                case COMPLETED -> {
                    initializePlayer();
                }
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SetLocalPlayerAsInitializedPacket packet) {
            //todo plugin event
            initialized.set(true);
            Server.getInstance().broadcastTr("§e%minecraft:multiplayer.player.joined", player.getOriginName());
            return PacketSignal.HANDLED;
        }
    }
}
