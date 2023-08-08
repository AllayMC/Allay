package cn.allay.server.client;

import cn.allay.api.annotation.SlowOperation;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.client.BaseClient;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.processor.ContainerActionProcessor;
import cn.allay.api.container.processor.ContainerActionProcessorHolder;
import cn.allay.api.data.VanillaEntityTypes;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.math.Location3d;
import cn.allay.api.math.Location3dc;
import cn.allay.api.math.Position3ic;
import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.data.LoginData;
import cn.allay.api.server.Server;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.gamerule.GameRule;
import cn.allay.api.container.SimpleContainerActionProcessorHolder;
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
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayClient extends BaseClient {

    private static final int DO_FIRST_SPAWN_CHUNK_THRESHOLD = 36;
    private final BedrockServerSession session;
    @Getter
    private boolean online = false;
    @Getter
    @Setter
    private boolean firstSpawned = false;
    private final AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(DO_FIRST_SPAWN_CHUNK_THRESHOLD);
    @Getter
    private final ContainerActionProcessorHolder containerActionProcessorHolder;
    @Setter
    private Function<SubChunkRequestPacket, SubChunkPacket> subChunkRequestHandler =
            packet -> {
                throw new UnsupportedOperationException();
            };

    private AllayClient(BedrockServerSession session, Server server) {
        this.session = session;
        this.server = server;
        this.chunkLoadingRadius = server.getServerSettings().defaultViewDistance();
        this.adventureSettings = new AdventureSettings(this);
        session.setPacketHandler(new AllayClientPacketHandler());
        containerActionProcessorHolder = new SimpleContainerActionProcessorHolder();
        ContainerActionProcessorHolder.registerDefaultContainerActionProcessors(containerActionProcessorHolder);
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

    @Override
    public boolean computeMovementServerSide() {
        return false;
    }

    public void sendPlayStatus(PlayStatusPacket.Status status) {
        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(status);
        sendPacket(playStatusPacket);
    }

    private void initPlayerEntity() {
        //TODO: Load player data
        Position3ic spawnPos = server.getDefaultWorld().getSpawnPosition();
        playerEntity = VanillaEntityTypes.PLAYER_TYPE.createEntity(
                new EntityPlayer.EntityPlayerInitInfo.Simple(
                        this,
                        new Location3d(
                                spawnPos.x(), spawnPos.y(), spawnPos.z(),
                                0, 0, 0,
                                spawnPos.world()
                        )
                )
        );
    }

    private void sendBasicGameData() {
        var spawnWorld = server.getDefaultWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().add(GameRule.SHOW_COORDINATES.toNetwork());
        startGamePacket.setUniqueEntityId(playerEntity.getUniqueId());
        startGamePacket.setRuntimeEntityId(playerEntity.getUniqueId());
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
    public Location3dc getLocation() {
        return playerEntity != null ? playerEntity.getLocation() : null;
    }

    @Override
    public boolean isLoaderActive() {
        return isOnline();
    }

    @SlowOperation
    @Override
    public void notifyChunkLoaded(Chunk chunk) {
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
//            settingsPacket.setCompressionThreshold(0);
            settingsPacket.setCompressionThreshold(1);
            sendPacketImmediately(settingsPacket);
            session.setCompression(settingsPacket.getCompressionAlgorithm());
            session.setCompressionLevel(settingsPacket.getCompressionThreshold());
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

            if (server.getServerSettings().enableNetworkEncryption()) {
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
                for (var action : request.getActions()) {
                    ContainerActionProcessor<ItemStackRequestAction> processor = containerActionProcessorHolder.getProcessor(action.getType());
                    if (processor == null) {
                        log.warn("Unhandled inventory action type " + action.getType());
                        continue;
                    }
                    responses.addAll(processor.handle(action, AllayClient.this, request.getRequestId()));
                }
            }
            var itemStackResponsePacket = new ItemStackResponsePacket();
            itemStackResponsePacket.getEntries().addAll(responses);
            sendPacket(itemStackResponsePacket);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SubChunkRequestPacket packet) {
            sendPacket(subChunkRequestHandler.apply(packet));
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(PlayerAuthInputPacket packet) {
            handleMovement(packet);
            handleBlockAction(packet.getPlayerActions());
            handleInputData(packet.getInputData());
            return PacketSignal.HANDLED;
        }

        protected void handleMovement(PlayerAuthInputPacket packet) {
            var newPos = packet.getPosition();
            var newRot = packet.getRotation();
            playerEntity.getLocation().world().getEntityPhysicsService()
                    .offerScheduledMove(
                            playerEntity,
                            new Location3d(
                                    newPos.getX(), newPos.getY(), newPos.getZ(),
                                    newRot.getX(), newRot.getY(), newRot.getZ(),
                                    getLocation().world())
                    );
        }

        protected void handleBlockAction(List<PlayerBlockActionData> blockActions) {
            if (blockActions.isEmpty()) return;
            for (var action : blockActions) {
                //TODO
            }
        }

        protected void handleInputData(Set<PlayerAuthInputData> inputData) {
            for (var input : inputData) {
                switch (input) {
                    case START_SPRINTING -> playerEntity.setSprinting(true);
                    case STOP_SPRINTING -> playerEntity.setSprinting(false);
                    case START_SNEAKING -> {
                        playerEntity.setSneaking(true);
                        //debug only
                        var loc = getLocation();
                        var entity = VanillaEntityTypes.VILLAGER_V2_TYPE.createEntity(new EntityInitInfo.Simple<>(new Location3d(loc)));
                        loc.world().addEntity(entity);
                    }
                    case STOP_SNEAKING -> playerEntity.setSneaking(false);
                    case START_SWIMMING -> playerEntity.setSwimming(true);
                    case STOP_SWIMMING -> playerEntity.setSwimming(false);
                    case START_GLIDING -> playerEntity.setGliding(true);
                    case STOP_GLIDING -> playerEntity.setGliding(false);
                    case START_CRAWLING -> playerEntity.setCrawling(true);
                    case STOP_CRAWLING -> playerEntity.setCrawling(false);
                    case START_JUMPING -> {/*TODO*/}
                }
            }
        }
    }
}
