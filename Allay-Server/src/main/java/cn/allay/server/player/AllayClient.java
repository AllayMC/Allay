package cn.allay.server.player;

import cn.allay.api.annotation.SlowOperation;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.processor.*;
import cn.allay.api.data.VanillaEntityTypes;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.math.vector.Loc3d;
import cn.allay.api.math.vector.Pos3i;
import cn.allay.api.player.AdventureSettings;
import cn.allay.api.player.Client;
import cn.allay.api.player.data.LoginData;
import cn.allay.api.server.Server;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.gamerule.GameRule;
import cn.allay.server.inventory.SimpleInventoryActionProcessorHolder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.jetbrains.annotations.Nullable;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayClient implements Client {

    private static final int DO_FIRST_SPAWN_CHUNK_THRESHOLD = 36;
    private final BedrockServerSession session;
    @Getter
    private boolean networkEncryptionEnabled;
    @Getter
    private SecretKey encryptionSecretKey;
    @Getter
    private final Server server;
    @Getter
    private final AdventureSettings adventureSettings;
    @Getter
    private LoginData loginData;
    @Getter
    private String name = "";
    @Getter
    private EntityPlayer playerEntity;
    @Getter
    private boolean online = false;
    @Getter
    private int chunkLoadingRadius;
    @Getter
    @Setter
    private boolean firstSpawned = false;
    @Getter
    @Setter
    private boolean op = true;
    @Getter
    @Setter
    private GameType gameType = GameType.CREATIVE;
    private final AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(DO_FIRST_SPAWN_CHUNK_THRESHOLD);
    @Getter
    private final InventoryActionProcessorHolder inventoryActionProcessorHolder;

    private AllayClient(BedrockServerSession session, Server server) {
        this.session = session;
        this.server = server;
        this.chunkLoadingRadius = server.getServerSettings().defaultViewDistance();
        this.adventureSettings = new AdventureSettings(this);
        session.setPacketHandler(new AllayClientPacketHandler());
        inventoryActionProcessorHolder = new SimpleInventoryActionProcessorHolder();
        InventoryActionProcessorHolder.registerDefaultInventoryActionProcessors(inventoryActionProcessorHolder);
    }

    public static AllayClient hold(BedrockServerSession session, Server Server) {
        return new AllayClient(session, Server);
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        chunkLoadingRadius = Math.min(radius, server.getServerSettings().defaultViewDistance());
        var chunkRadiusUpdatedPacket = new ChunkRadiusUpdatedPacket();
        chunkRadiusUpdatedPacket.setRadius(chunkLoadingRadius);
        sendPacket(chunkRadiusUpdatedPacket);
    }

    @Override
    public void preSendChunks(Set<Long> chunkHashes) {
        var chunkPublisherUpdatePacket = new NetworkChunkPublisherUpdatePacket();
        var loc = getLocation();
        chunkPublisherUpdatePacket.setPosition(Vector3i.from(loc.x(), loc.y(), loc.z()));
        chunkPublisherUpdatePacket.setRadius(getChunkLoadingRadius() << 4);

        sendPacket(chunkPublisherUpdatePacket);
    }

    private void doFirstSpawn() {
        if (firstSpawned) {
            return;
        }

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(playerEntity.getUniqueId());
        setEntityDataPacket.getMetadata().putAll(playerEntity.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(server.getTicks());
        sendPacket(setEntityDataPacket);

        adventureSettings.set(AdventureSettings.Type.OPERATOR, isOp());
        adventureSettings.set(AdventureSettings.Type.TELEPORT, true);
        adventureSettings.set(AdventureSettings.Type.WORLD_IMMUTABLE, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.ALLOW_FLIGHT, gameType != GameType.SURVIVAL && gameType != GameType.ADVENTURE);
        adventureSettings.set(AdventureSettings.Type.NO_CLIP, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.FLYING, gameType == GameType.SPECTATOR);
        adventureSettings.set(AdventureSettings.Type.ATTACK_MOBS, gameType == GameType.SURVIVAL || gameType == GameType.CREATIVE);
        adventureSettings.set(AdventureSettings.Type.ATTACK_PLAYERS, gameType == GameType.SURVIVAL || gameType == GameType.CREATIVE);
        adventureSettings.set(AdventureSettings.Type.NO_PVM, gameType == GameType.SPECTATOR);
        adventureSettings.update();

        //TODO: CommandData

        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(playerEntity.getUniqueId());
        for (Attribute attribute : playerEntity.getAttributes()) {
            updateAttributesPacket.getAttributes().add(attribute.toNetwork());
        }
        updateAttributesPacket.setTick(server.getTicks());
        sendPacket(updateAttributesPacket);

        server.addToPlayerList(this);

        sendInventories();

        sendPlayStatus(PlayStatusPacket.Status.PLAYER_SPAWN);

        //TODO: SetTime

        firstSpawned = true;
    }

    private void sendInventories() {
        var inv = playerEntity.getContainer(FullContainerType.PLAYER_INVENTORY);
        //TODO: setHolder
        inv.sendContents(playerEntity);

        var cursor = playerEntity.getContainer(FullContainerType.CURSOR);
        cursor.sendContents(playerEntity);

        var armor = playerEntity.getContainer(FullContainerType.ARMOR);
        armor.sendContents(playerEntity);
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
    public void disconnect(String reason) {
        disconnect(reason, false);
    }

    @Override
    public void disconnect(String reason, boolean hideReason) {
        server.onClientDisconnect(this);
        session.disconnect(reason, hideReason);
        if (playerEntity != null)
            playerEntity.getLocation().world().removeClient(this);
    }

    /**
     * 登入完成后调用。发送玩家需要的数据并生成玩家实体。此时可以认为玩家已进服但未加载完成
     */
    @Override
    public void initializePlayer() {
        server.onLoginFinish(this);
        initPlayerEntity();
        sendBasicGameData();
        online = true;
        server.getDefaultWorld().addClient(this);
    }

    public void sendPlayStatus(PlayStatusPacket.Status status) {
        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(status);
        sendPacket(playStatusPacket);
    }

    private void initPlayerEntity() {
        //TODO: Load player data
        Pos3i spawnPos = server.getDefaultWorld().getSpawnPosition();
        playerEntity = VanillaEntityTypes.PLAYER_TYPE.createEntity(new EntityInitInfo.Simple(Loc3d.of(spawnPos.x(), spawnPos.y(), spawnPos.z(), 0, 0, 0, spawnPos.world())));
        playerEntity.setClient(this);
    }

    private void sendBasicGameData() {
        var spawnWorld = server.getDefaultWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().add(GameRule.SHOW_COORDINATES.toNetwork());
        startGamePacket.setUniqueEntityId(playerEntity.getUniqueId());
        //TODO: WOC?
        startGamePacket.setRuntimeEntityId(playerEntity.getUniqueId());
        //TODO
        startGamePacket.setPlayerGameType(gameType);
        var loc = playerEntity.getLocation();
        var worldSpawn = spawnWorld.getSpawnPosition();
        startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        startGamePacket.setSeed(0L);
        startGamePacket.setDimensionId(spawnWorld.getDimensionInfo().dimensionId());
        startGamePacket.setGeneratorId(spawnWorld.getWorldGenerator().getGeneratorWorldType().getId());
        startGamePacket.setLevelGameType(spawnWorld.getWorldGameType());
        startGamePacket.setDifficulty(spawnWorld.getDifficulty().ordinal());
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setDayCycleStopTime(0);
        startGamePacket.setLevelName(server.getServerSettings().motd());
        //TODO
        startGamePacket.setLevelId("");
        //TODO
        startGamePacket.setDefaultPlayerPermission(PlayerPermission.OPERATOR);
        startGamePacket.setServerChunkTickRange(spawnWorld.getTickingRadius());
        startGamePacket.setVanillaVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
        startGamePacket.setPremiumWorldTemplateId("");
        startGamePacket.setInventoriesServerAuthoritative(true);
        //TODO
        startGamePacket.setItemDefinitions(ItemTypeRegistry.getRegistry().getItemDefinitions());
        //TODO: server-auth-movement
        startGamePacket.setAuthoritativeMovementMode(AuthoritativeMovementMode.CLIENT);
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
        //Hashed runtime ids
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

        var craftingDataPacket = new CraftingDataPacket();
        craftingDataPacket.setCleanRecipes(true);
        sendPacket(craftingDataPacket);
    }

    @Override
    @Nullable
    public Loc3d getLocation() {
        return playerEntity != null ? playerEntity.getLocation() : null;
    }

    @Override
    public boolean isLoaderActive() {
        return isOnline();
    }

    @SlowOperation
    @Override
    public void sendChunk(Chunk chunk) {
        var levelChunkPacket = chunk.createLevelChunkPacket();
        sendPacket(levelChunkPacket);
        if (doFirstSpawnChunkThreshold.get() > 0) {
            if (doFirstSpawnChunkThreshold.decrementAndGet() == 0) {
                doFirstSpawn();
            }
        }
    }

    @Override
    public void unloadChunks(Set<Long> chunkHashes) {

    }

    private class AllayClientPacketHandler implements BedrockPacketHandler {

        public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

        @Override
        public void onDisconnect(String reason) {
            server.onClientDisconnect(AllayClient.this);
            var loc = getLocation();
            if (loc != null)
                loc.world().removeClient(AllayClient.this);
        }

        @Override
        public PacketSignal handle(RequestNetworkSettingsPacket packet) {
            var protocolVersion = packet.getProtocolVersion();
            var supportedProtocolVersion = Server.getInstance().getNetworkServer().getCodec().getProtocolVersion();
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
            //TODO: Support other compression algorithms
            settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
            settingsPacket.setCompressionThreshold(0);
//            settingsPacket.setCompressionThreshold(1);
            sendPacketImmediately(settingsPacket);
            session.setCompression(settingsPacket.getCompressionAlgorithm());
//            session.setCompressionLevel(settingsPacket.getCompressionThreshold());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(LoginPacket packet) {
            loginData = LoginData.decode(packet);

            //TODO: event
            if (!loginData.isXboxAuthenticated() && server.getServerSettings().xboxAuth()) {
                disconnect("disconnectionScreen.notAuthenticated");
                return PacketSignal.HANDLED;
            }

            name = loginData.getDisplayName();
            if (!NAME_PATTERN.matcher(name).matches()) {
                disconnect("disconnectionScreen.invalidName");
                return PacketSignal.HANDLED;
            }

            if (server.getOnlineClients().containsKey(name)) {
                disconnect("disconnectionScreen.loggedinOtherLocation");
                return PacketSignal.HANDLED;
            }

            if (!loginData.getSkin().isValid()) {
                session.disconnect("disconnectionScreen.invalidSkin");
                return PacketSignal.HANDLED;
            }

            if (!server.getServerSettings().enableNetworkEncryption()) {
                completeLogin();
            } else {
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
            }

            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ClientToServerHandshakePacket packet) {
            if (isNetworkEncryptionEnabled()) {
                completeLogin();
            } else log.warn("Client " + name + " sent ClientToServerHandshakePacket without encryption enabled");
            return PacketSignal.HANDLED;
        }

        protected void completeLogin() {
            var playStatusPacket = new PlayStatusPacket();
            if (server.getOnlineClientCount() >= server.getServerSettings().maxClientCount()) {
                playStatusPacket.setStatus(PlayStatusPacket.Status.FAILED_SERVER_FULL_SUB_CLIENT);
            } else {
                playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
            }
            sendPacket(playStatusPacket);
            //TODO: Resource Packs
            sendPacket(new ResourcePacksInfoPacket());
        }

        @Override
        public PacketSignal handle(ResourcePackClientResponsePacket packet) {
            switch (packet.getStatus()) {
                case SEND_PACKS -> {
                    //TODO: RP
                }
                case HAVE_ALL_PACKS -> {
                    var stackPacket = new ResourcePackStackPacket();
                    stackPacket.setGameVersion(server.getNetworkServer().getCodec().getMinecraftVersion());
                    sendPacket(stackPacket);
                }
                case COMPLETED -> {
                    initializePlayer();
                }
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(RequestChunkRadiusPacket packet) {
            setChunkLoadingRadius(packet.getRadius());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(MovePlayerPacket packet) {
            var pos = packet.getPosition();
            var rot = packet.getRotation();
            playerEntity.setLocation(Loc3d.of(
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    rot.getX(),
                    rot.getY(),
                    rot.getZ(),
                    getLocation().world()
            ));
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SetLocalPlayerAsInitializedPacket packet) {
            //TODO: PlayerJoinEvent
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(InteractPacket packet) {
            switch (packet.getAction()) {
                case OPEN_INVENTORY -> {
                    playerEntity.getContainer(FullContainerType.PLAYER_INVENTORY).addViewer(playerEntity);
                }
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ContainerClosePacket packet) {
            var opened = playerEntity.getOpenedContainer(packet.getId());
            if (opened == null)
                throw new IllegalStateException("Player is not viewing an inventory");
            opened.removeViewer(playerEntity);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ItemStackRequestPacket packet) {
            List<ItemStackResponse> responses = new LinkedList<>();
            for (var request : packet.getRequests()) {
                handleItemStackRequest(request, responses);
            }
            var itemStackResponsePacket = new ItemStackResponsePacket();
            itemStackResponsePacket.getEntries().addAll(responses);
            sendPacket(itemStackResponsePacket);
            return PacketSignal.HANDLED;
        }

        private void handleItemStackRequest(ItemStackRequest request, List<ItemStackResponse> responses) {
            for (var action : request.getActions()) {
                var processor = inventoryActionProcessorHolder.getProcessor(action.getType());
                if (processor == null) {
                    log.warn("Unhandled inventory action type " + action.getType());
                    continue;
                }
                //TODO: 使用访问者模式将方法调用移动到ActionProcessor
                switch (action.getType()) {
                    case CRAFT_CREATIVE -> {
                        var craftCreativeAction = (CraftCreativeAction) action;
                        responses.addAll(((CraftCreativeActionProcessor) processor).handle(
                                craftCreativeAction,
                                request.getRequestId(),
                                playerEntity.getContainer(FullContainerType.CREATED_OUTPUT),
                                0));
                    }
                    case TAKE, PLACE -> {
                        var transferAction = (TransferItemStackRequestAction) action;
                        var slot1 = transferAction.getSource().getSlot();
                        var stackNetworkId1 = transferAction.getSource().getStackNetworkId();
                        var slot2 = transferAction.getDestination().getSlot();
                        var stackNetworkId2 = transferAction.getDestination().getStackNetworkId();
                        var source = playerEntity.getContainerBySlotType(transferAction.getSource().getContainer());
                        var destination = playerEntity.getContainerBySlotType(transferAction.getDestination().getContainer());
                        Objects.requireNonNull(source, "source container");
                        Objects.requireNonNull(destination, "destination container");
                        responses.addAll(((TransferItemActionProcessor) processor).handle(
                                request.getRequestId(),
                                source, slot1, stackNetworkId1,
                                destination, slot2, stackNetworkId2,
                                transferAction.getCount()));
                    }
                    case DESTROY -> {
                        var destroyAction = (DestroyAction) action;
                        Objects.requireNonNull(playerEntity.getContainerBySlotType(destroyAction.getSource().getContainer()), "source container");
                        responses.addAll(((DestroyActionProcessor) processor).handle(
                                request.getRequestId(),
                                playerEntity.getContainerBySlotType(destroyAction.getSource().getContainer()),
                                destroyAction.getSource().getSlot(),
                                destroyAction.getSource().getStackNetworkId(),
                                destroyAction.getCount()
                        ));
                    }
                }
            }
        }
    }
}
