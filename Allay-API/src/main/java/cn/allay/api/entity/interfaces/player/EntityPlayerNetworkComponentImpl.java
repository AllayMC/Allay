package cn.allay.api.entity.interfaces.player;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.interfaces.BlockAirBehavior;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.data.LoginData;
import cn.allay.api.client.movement.ClientMovementValidator;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.ComponentedObject;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.container.Container;
import cn.allay.api.container.FixedContainerId;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.SimpleContainerActionProcessorHolder;
import cn.allay.api.container.processor.ContainerActionProcessor;
import cn.allay.api.container.processor.ContainerActionProcessorHolder;
import cn.allay.api.entity.attribute.Attribute;
import cn.allay.api.entity.init.SimpleEntityInitInfo;
import cn.allay.api.entity.interfaces.villagerv2.EntityVillagerV2;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.CreativeItemRegistry;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.position.Position3ic;
import cn.allay.api.server.Server;
import cn.allay.api.utils.MathUtils;
import cn.allay.api.world.biome.BiomeTypeRegistry;
import cn.allay.api.world.gamerule.GameRule;
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
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.bedrock.util.EncryptionUtils;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import java.util.*;
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
    //登录验证已完成
    protected final AtomicBoolean loggedIn = new AtomicBoolean(false);
    //玩家实体已加入世界
    protected final AtomicBoolean online = new AtomicBoolean(false);
    //预发送区块发送完毕，已通知客户端完成加载
    protected final AtomicBoolean firstSpawned = new AtomicBoolean(false);
    //客户端已返回加载完成包，完全进服
    protected final AtomicBoolean localInitialized = new AtomicBoolean(false);
    protected final Server server = Server.getInstance();
    @Getter
    protected final ContainerActionProcessorHolder containerActionProcessorHolder;
    protected final AtomicInteger doFirstSpawnChunkThreshold = new AtomicInteger(Server.getInstance().getServerSettings().worldSettings().doFirstSpawnChunkThreshold());
    @Manager
    protected ComponentManager<EntityPlayer> manager;
    @Getter
    protected LoginData loginData;
    @Getter
    protected boolean networkEncryptionEnabled = false;
    @Getter
    @Nullable
    protected SecretKey encryptionSecretKey;
    @Getter
    @Setter
    protected ClientMovementValidator movementValidator = ClientMovementValidator.EMPTY_VALIDATOR;
    @ComponentedObject
    protected EntityPlayer player;
    protected BedrockServerSession session;

    public EntityPlayerNetworkComponentImpl() {
        containerActionProcessorHolder = new SimpleContainerActionProcessorHolder();
        ContainerActionProcessorHolder.registerDefaultContainerActionProcessors(containerActionProcessorHolder);
    }

    @Override
    public boolean isLoggedIn() {
        return loggedIn.get();
    }

    @Override
    public boolean isOnline() {
        return online.get();
    }

    @Override
    public boolean isFirstSpawned() {
        return firstSpawned.get();
    }

    @Override
    public boolean isLocalInitialized() {
        return localInitialized.get();
    }

    @Override
    public void setClientSession(BedrockServerSession session) {
        this.session = session;
        session.setPacketHandler(new AllayClientPacketHandler());
    }

    @Override
    public void onChunkInRangeLoaded() {
        if (doFirstSpawnChunkThreshold.get() > 0) {
            if (doFirstSpawnChunkThreshold.decrementAndGet() == 0) {
                doFirstSpawn();
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
    public void disconnect(String reason) {
        session.disconnect(reason);
    }

    @Override
    public void disconnect(String reason, boolean hideReason) {
        session.disconnect(reason, hideReason);
    }

    protected void doFirstSpawn() {
        if (firstSpawned.get()) {
            return;
        }
        server.getPlayerStorage().readPlayerData(player);

        var setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setRuntimeEntityId(player.getUniqueId());
        setEntityDataPacket.getMetadata().putAll(player.getMetadata().getEntityDataMap());
        setEntityDataPacket.setTick(server.getTicks());
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

        //TODO: CommandData

        var updateAttributesPacket = new UpdateAttributesPacket();
        updateAttributesPacket.setRuntimeEntityId(player.getUniqueId());
        for (Attribute attribute : player.getAttributes()) {
            updateAttributesPacket.getAttributes().add(attribute.toNetwork());
        }
        updateAttributesPacket.setTick(server.getTicks());
        sendPacket(updateAttributesPacket);

        server.addToPlayerList(player);
        if (server.getOnlinePlayerCount() > 1) {
            server.sendFullPlayerListInfoTo(player);
        }

        sendInventories();

        firstSpawned.set(true);
        var playStatusPacket = new PlayStatusPacket();
        playStatusPacket.setStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
        sendPacket(playStatusPacket);

        //TODO: SetTime
    }

    private void sendInventories() {
        //TODO: setHolder
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.PLAYER_INVENTORY), FixedContainerId.PLAYER_INVENTORY);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.OFFHAND), FixedContainerId.OFFHAND);
        player.sendContentsWithSpecificContainerId(player.getContainer(FullContainerType.ARMOR), FixedContainerId.ARMOR);
        //No need to send cursor's content to client because there is nothing in cursor
    }

    private void initializePlayer() {
        //TODO: save spawn world per player
        Position3ic spawnPos = server.getDefaultWorld().getSpawnPosition();
        player.setLocation(new Location3f(spawnPos.x(), spawnPos.y(), spawnPos.z(), spawnPos.world()));
        sendBasicGameData();
        spawnPos.world().getChunkService().getOrLoadChunk(
                (int) player.getLocation().x() >> 4,
                (int) player.getLocation().z() >> 4
        ).thenAcceptAsync((c) -> {
            spawnPos.world().addPlayer(player);
            online.set(true);
        }, Server.getInstance().getVirtualThreadPool()).exceptionally(
                t -> {
                    log.error("Error while initialize player " + getOriginName() + "!", t);
                    return null;
                }
        );
    }

    private void sendBasicGameData() {
        var spawnWorld = server.getDefaultWorld();
        var startGamePacket = new StartGamePacket();
        startGamePacket.getGamerules().add(GameRule.SHOW_COORDINATES.toNetwork());
        startGamePacket.setUniqueEntityId(player.getUniqueId());
        startGamePacket.setRuntimeEntityId(player.getUniqueId());
        startGamePacket.setPlayerGameType(player.getGameType());
        var loc = player.getLocation();
        var worldSpawn = spawnWorld.getSpawnPosition();
        startGamePacket.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        startGamePacket.setPlayerPosition(Vector3f.from(loc.x(), loc.y(), loc.z()));
        startGamePacket.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        startGamePacket.setSeed(spawnWorld.getWorldData().getRandomSeed());
        startGamePacket.setDimensionId(spawnWorld.getDimensionInfo().dimensionId());
        startGamePacket.setGeneratorId(spawnWorld.getWorldGenerator().getType().getId());
        startGamePacket.setLevelGameType(spawnWorld.getWorldGameType());
        startGamePacket.setDifficulty(spawnWorld.getDifficulty().ordinal());
        startGamePacket.setTrustingPlayers(true);
        startGamePacket.setDayCycleStopTime(0);
        startGamePacket.setLevelName(server.getServerSettings().genericSettings().motd());
        //TODO
        startGamePacket.setLevelId("");
        //TODO
        startGamePacket.setDefaultPlayerPermission(server.getServerSettings().genericSettings().defaultPermission());
        startGamePacket.setServerChunkTickRange(spawnWorld.getTickingRadius());
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

        var craftingDataPacket = new CraftingDataPacket();
        craftingDataPacket.setCleanRecipes(true);
        sendPacket(craftingDataPacket);
    }

    private class AllayClientPacketHandler implements BedrockPacketHandler {

        public static final Pattern NAME_PATTERN = Pattern.compile("^(?! )([a-zA-Z0-9_ ]{2,15}[a-zA-Z0-9_])(?<! )$");

        @Override
        public void onDisconnect(String reason) {
            if (firstSpawned.get()) server.getPlayerStorage().writePlayerData(player);
            server.onDisconnect(player);
            if (online.get())
                player.getLocation().world().removePlayer(player);
        }

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
            settingsPacket.setCompressionAlgorithm(server.getServerSettings().networkSettings().compressionAlgorithm());
            settingsPacket.setCompressionThreshold(server.getServerSettings().networkSettings().compressionThreshold());
            sendPacketImmediately(settingsPacket);
            session.setCompression(settingsPacket.getCompressionAlgorithm());
            session.setCompressionLevel(settingsPacket.getCompressionThreshold());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(LoginPacket packet) {
            loginData = LoginData.decode(packet);

            if (!loginData.isXboxAuthenticated() && server.getServerSettings().networkSettings().xboxAuth()) {
                disconnect("disconnectionScreen.notAuthenticated");
                return PacketSignal.HANDLED;
            }

            var name = loginData.getDisplayName();
            if (!NAME_PATTERN.matcher(name).matches()) {
                disconnect("disconnectionScreen.invalidName");
                return PacketSignal.HANDLED;
            }

            if (server.getOnlinePlayers().containsKey(name)) {
                disconnect("disconnectionScreen.loggedinOtherLocation");
                return PacketSignal.HANDLED;
            }

            if (!loginData.getSkin().isValid()) {
                session.disconnect("disconnectionScreen.invalidSkin");
                return PacketSignal.HANDLED;
            }

            if (server.getServerSettings().networkSettings().enableNetworkEncryption()) {
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
            if (server.getOnlinePlayerCount() >= server.getServerSettings().genericSettings().maxClientCount()) {
                playStatusPacket.setStatus(PlayStatusPacket.Status.FAILED_SERVER_FULL_SUB_CLIENT);
            } else {
                playStatusPacket.setStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
            }
            sendPacket(playStatusPacket);
            server.onLoggedIn(player);
            loggedIn.set(true);
            manager.callEvent(PlayerLoggedInEvent.INSTANCE);
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
            player.setChunkLoadingRadius(packet.getRadius());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SetLocalPlayerAsInitializedPacket packet) {
            localInitialized.set(true);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(InteractPacket packet) {
            switch (packet.getAction()) {
                case OPEN_INVENTORY -> {
                    player.getContainer(FullContainerType.PLAYER_INVENTORY).addViewer(player);
                }
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ContainerClosePacket packet) {
            var opened = player.getOpenedContainer(packet.getId());
            if (opened == null)
                throw new IllegalStateException("Player is not viewing an inventory");
            opened.removeViewer(player);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(ItemStackRequestPacket packet) {
            List<ItemStackResponse> responses = new LinkedList<>();
            for (var request : packet.getRequests()) {
                var chainInfo = new LinkedHashMap<ItemStackRequestActionType, ItemStackResponse>();
                //chain process
                for (var action : request.getActions()) {
                    ContainerActionProcessor<ItemStackRequestAction> processor = containerActionProcessorHolder.getProcessor(action.getType());
                    if (processor == null) {
                        log.warn("Unhandled inventory action type " + action.getType());
                        continue;
                    }
                    chainInfo.put(action.getType(), processor.handle(action, player, request.getRequestId(), chainInfo));
                }
                //add process result
                for (var res : chainInfo.values()) {
                    if (res != null) {
                        responses.add(res);
                    }
                }
            }
            var itemStackResponsePacket = new ItemStackResponsePacket();
            itemStackResponsePacket.getEntries().addAll(responses);
            sendPacket(itemStackResponsePacket);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(SubChunkRequestPacket packet) {
            sendPacket(player.getSubChunkRequestHandler().apply(packet));
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(MobEquipmentPacket packet) {
            var handSlot = packet.getHotbarSlot();
            player.setHandSlot(handSlot);
            return PacketSignal.HANDLED;
        }

        protected long spamCheckTime;

        @Override
        public PacketSignal handle(InventoryTransactionPacket packet) {
            if (packet.getTransactionType() == InventoryTransactionType.ITEM_USE) {
                Vector3ic blockPos = MathUtils.cbVecToJOMLVec(packet.getBlockPosition());
                Vector3fc clickPos = MathUtils.cbVecToJOMLVec(packet.getClickPosition());
                BlockFace blockFace = BlockFace.fromId(packet.getBlockFace());
                var inv = player.getContainer(FullContainerType.PLAYER_INVENTORY);
                var itemStack = inv.getItemInHand();
                var world = player.getLocation().world();
                switch (packet.getActionType()) {
                    case 0 -> {
                        var placePos = blockFace.offsetPos(blockPos);
                        if (!canInteract()) {
                            //TODO: 确认是否需要发送UpdateBlockPacket
                            var blockState = world.getBlockState(placePos.x(), placePos.y(), placePos.z());
                            world.sendBlockUpdateTo(blockState, placePos.x(), placePos.y(), placePos.z(), 0, player);
                            return PacketSignal.HANDLED;
                        }
                        this.spamCheckTime = System.currentTimeMillis();

                        if (!useItemOn(itemStack, blockPos, placePos, clickPos, blockFace)) {
                            //Failed to use the item, send back origin block state to client
                            var w = player.getLocation().world();
                            var blockStateClicked = w.getBlockState(blockPos.x(), blockPos.y(), blockPos.z());
                            w.sendBlockUpdateTo(blockStateClicked, blockPos.x(), blockPos.y(), blockPos.z(), 0, player);

                            var blockStateReplaced = w.getBlockState(placePos.x(), placePos.y(), placePos.z());
                            w.sendBlockUpdateTo(blockStateReplaced, placePos.x(), placePos.y(), placePos.z(), 0, player);
                        } else {
                            //Used! Update item slot to client
                            if (itemStack.getCount() != 0) {
                                inv.onSlotChange(inv.getHandSlot());
                            } else {
                                inv.setItemInHand(Container.EMPTY_SLOT_PLACE_HOLDER);
                            }
                        }
                    }
                }
            } else if (packet.getTransactionType() == InventoryTransactionType.NORMAL) {
                for (var action : packet.getActions()) {
                    if (action.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) {
                        if (action.getSource().getFlag().equals(InventorySource.Flag.DROP_ITEM)) {
                            //Do not ask me why mojang still use the old item transaction packet even the server-auth inv was enabled
                            var count = action.getToItem().getCount();
                            player.tryDropItemInHand(count);
                        }
                    }
                }
            }
            return PacketSignal.HANDLED;
        }

        private boolean useItemOn(ItemStack itemStack, Vector3ic blockPos, Vector3ic placePos, Vector3fc clickPos, BlockFace blockFace) {
            var world = player.getLocation().world();
            var blockStateClicked = world.getBlockState(blockPos.x(), blockPos.y(), blockPos.z());
            if (!blockStateClicked.getBehavior().onInteract(player, itemStack, world, blockPos, placePos, clickPos, blockFace))
                return itemStack.useItemOn(player, itemStack, world, blockPos, placePos, clickPos, blockFace);
            else return true;
        }

        protected boolean canInteract() {
            return System.currentTimeMillis() - this.spamCheckTime >= 100;
        }

        @Override
        public PacketSignal handle(PlayerAuthInputPacket packet) {
            if (!isOnline()) return PacketSignal.UNHANDLED;
            //客户端发送给服务端的坐标比实际坐标高了一个BaseOffset，我们需要减掉它
            handleMovement(packet.getPosition().sub(0, player.getBaseOffset(), 0), packet.getRotation());
            handleBlockAction(packet.getPlayerActions());
            handleInputData(packet.getInputData());
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(MovePlayerPacket packet) {
            //In server-auth movement, the MovePlayerPacket is only used to send "onGround" state to server by client
            if (!isOnline()) return PacketSignal.UNHANDLED;
            if (!packet.isOnGround()) {
                log.warn("Player " + getOriginName() + " send a invalid MovePlayerPacket (onGround=false) while using server-auth movement!");
                return PacketSignal.HANDLED;
            }
            if (!movementValidator.validateOnGround()) {
                log.warn("Player " + getOriginName() + " thinks he landed but didn't in fact!");
                return PacketSignal.HANDLED;
            }
            player.setOnGround(true);
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(AnimatePacket packet) {
            if (packet.getAction() == AnimatePacket.Action.SWING_ARM) {
                player.getCurrentChunk().addChunkPacket(packet);
            }
            return PacketSignal.HANDLED;
        }

        @Override
        public PacketSignal handle(TextPacket packet) {
            if (packet.getType() == TextPacket.Type.CHAT) {
                server.broadcastChat(player, packet.getMessage());
                //TODO: debug only
                if (packet.getMessage().equals("spawn v")) {
                    var loc = player.getLocation();
                    for (var i = 0; i <= 0; i++) {
                        var entity = EntityVillagerV2.VILLAGER_V2_TYPE.createEntity(
                                SimpleEntityInitInfo
                                        .builder()
                                        .pos(loc.x() + i, loc.y(), loc.z() + i)
                                        .world(loc.world())
                                        .build()
                        );
                        loc.world().addEntity(entity);
                    }
                    player.sendRawMessage("TPS: " + loc.world().getTps() + ", Entity Count: " + loc.world().getEntities().size());
                }
                if (packet.getMessage().equals("test2")) {
                    player.getMetadata().setFlag(EntityFlag.USING_ITEM, true);
                    player.sendEntityFlags(EntityFlag.USING_ITEM);
                    var pk = new SetEntityDataPacket();
                    pk.setRuntimeEntityId(player.getUniqueId());
                    pk.getMetadata().setFlag(EntityFlag.USING_ITEM, true);
                    pk.setTick(Server.getInstance().getTicks());
                    Server.getInstance().broadcastPacket(pk);
                }
            }
            return PacketSignal.HANDLED;
        }

        protected void handleMovement(Vector3f newPos, Vector3f newRot) {
            var world = player.getLocation().world();
            var valid = movementValidator.validate(new Location3f(
                    newPos.getX(), newPos.getY(), newPos.getZ(),
                    newRot.getX(), newRot.getY(), newRot.getZ(),
                    world)
            );
            if (!valid) {
                log.warn("Player " + getOriginName() + " tried to move to invalid location");
                return;
            }
            world.getEntityPhysicsService()
                    .offerScheduledMove(
                            player,
                            new Location3f(
                                    newPos.getX(), newPos.getY(), newPos.getZ(),
                                    newRot.getX(), newRot.getY(), newRot.getZ(),
                                    world)
                    );
        }

        protected void handleBlockAction(List<PlayerBlockActionData> blockActions) {
            if (blockActions.isEmpty()) return;
            var world = player.getLocation().world();
            for (var action : blockActions) {
                var pos = action.getBlockPosition();
                //TODO: checking
                switch (action.getAction()) {
                    case START_BREAK -> {
                        world.sendLevelEventPacket(pos, LevelEvent.BLOCK_START_BREAK, 0);
                    }
                    case BLOCK_PREDICT_DESTROY -> {
                        var oldState = world.getBlockState(pos.getX(), pos.getY(), pos.getZ());
                        world.setBlockState(pos.getX(), pos.getY(), pos.getZ(), BlockAirBehavior.AIR_TYPE.getDefaultState());
                        world.sendLevelEventPacket(pos, LevelEvent.BLOCK_STOP_BREAK, 0);
                        world.sendLevelEventPacket(pos, LevelEvent.PARTICLE_DESTROY_BLOCK, oldState.blockStateHash());
                    }
                }
            }
        }

        protected void handleInputData(Set<PlayerAuthInputData> inputData) {
            for (var input : inputData) {
                switch (input) {
                    case START_SPRINTING -> player.setSprinting(true);
                    case STOP_SPRINTING -> player.setSprinting(false);
                    case START_SNEAKING -> player.setSneaking(true);
                    case STOP_SNEAKING -> player.setSneaking(false);
                    case START_SWIMMING -> player.setSwimming(true);
                    case STOP_SWIMMING -> player.setSwimming(false);
                    case START_GLIDING -> player.setGliding(true);
                    case STOP_GLIDING -> player.setGliding(false);
                    case START_CRAWLING -> player.setCrawling(true);
                    case STOP_CRAWLING -> player.setCrawling(false);
                    case START_JUMPING -> player.setOnGround(false);
                }
            }
        }
    }
}