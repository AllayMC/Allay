package org.allaymc.server.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.ddui.DDUIScreenSession;
import org.allaymc.api.ddui.type.DDUIScreen;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.server.PlayerAbilitiesUpdateEvent;
import org.allaymc.api.eventbus.event.server.PlayerDisconnectEvent;
import org.allaymc.api.eventbus.event.server.PlayerLoginEvent;
import org.allaymc.api.eventbus.event.server.PlayerSpawnEvent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.OpPermissionCalculator;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.*;
import org.allaymc.api.primitiveshape.PrimitiveShape;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.allaymc.server.AllayServer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.command.tree.node.BaseNode;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.AbstractPlayerContainer;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.server.ddui.AllayDDUIScreenSession;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.RequestNetworkSettingsPacketProcessor;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ProtocolSession;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.AttributeData;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;
import org.cloudburstmc.protocol.bedrock.data.ScoreInfo;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3dc;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.awt.image.BufferedImage;
import java.net.SocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.allaymc.api.utils.AllayNBTUtils.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayPlayer implements Player {

    protected final PacketProcessorHolder bootstrapProcessorHolder;
    private final Object protocolLifecycleLock = new Object();
    @Getter
    protected volatile ProtocolSession protocolSession;
    protected final AtomicInteger fullyJoinChunkThreshold;
    @Getter
    protected final BedrockServerSession session;
    @Getter
    protected final AllayNetworkInterface sourceInterface;

    @Getter
    protected EntityPlayer controlledEntity;
    @Getter
    @Setter
    protected AllayLoginData loginData;
    @Getter
    @Setter
    protected boolean networkEncryptionEnabled;
    @Getter
    @Setter
    protected boolean clientCacheEnabled;
    protected boolean shouldSendCommands;
    @Getter
    @Setter
    protected boolean containerClosedByClient;
    @Setter
    protected boolean suppressNextContainerClosePacket;
    @Getter
    protected Speed speed, flySpeed, verticalFlySpeed;
    @Getter
    @Setter
    protected InputMode inputMode;
    @Getter
    @Setter
    protected ClientPlayMode playMode;
    @Getter
    @Setter
    protected InputInteractionModel inputInteractionModel;

    @Getter
    protected volatile boolean changingDimension;

    // Container
    protected byte containerIdCounter;
    protected BiMap<Byte, Container> idToContainer;
    protected BiMap<ContainerType<?>, Container> typeToContainer;
    protected Map<ContainerSlotType, ContainerType<?>> slotTypeToFullType;

    // Form
    protected AtomicInteger formIdCounter;
    protected Cache<@NotNull Integer, Form> forms;
    @Getter
    protected Pair<Integer, CustomForm> serverSettingForm;
    protected DDUIScreenSession activeScreen;

    // Dialog
    protected Pair<Dialog, Entity> dialog;

    // Hud
    protected Set<HudElement> hiddenHudElements;
    protected boolean shouldSendHudElements;

    // Fog
    protected final List<String> fogStack = new ArrayList<>();

    // Abilities
    protected final EnumSet<PlayerAbility> abilities;
    protected boolean shouldSendAbilities;
    protected boolean immutableWorld;
    protected boolean alwaysFlying;

    // NetEase
    @Getter
    @Setter
    protected boolean netEasePlayer;

    public AllayPlayer(BedrockServerSession session, AllayNetworkInterface sourceInterface) {
        this.session = session;
        this.sourceInterface = sourceInterface;
        this.session.setPacketHandler(new AllayPacketHandler());
        this.bootstrapProcessorHolder = createBootstrapProcessorHolder();
        this.fullyJoinChunkThreshold = new AtomicInteger(AllayServer.getSettings().worldSettings().fullyJoinChunkThreshold());
        this.speed = DEFAULT_SPEED;
        this.flySpeed = DEFAULT_FLY_SPEED;
        this.verticalFlySpeed = DEFAULT_VERTICAL_FLY_SPEED;

        // Container
        this.containerIdCounter = 1;
        this.idToContainer = HashBiMap.create(new Byte2ObjectOpenHashMap<>());
        this.typeToContainer = HashBiMap.create(new Object2ObjectOpenHashMap<>());
        this.slotTypeToFullType = new HashMap<>();

        // Form
        this.formIdCounter = new AtomicInteger(0);
        this.forms = Caffeine.newBuilder()
                // A player can only have 100 opened forms at a time
                .maximumSize(100)
                // Opened form will be expired after 10 minutes
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();

        // Hud
        this.hiddenHudElements = EnumSet.noneOf(HudElement.class);

        // Abilities
        this.abilities = EnumSet.noneOf(PlayerAbility.class);
    }

    private static PacketProcessorHolder createBootstrapProcessorHolder() {
        var registry = new PacketProcessorRegistry();
        registry.register(
                ClientState.CONNECTED,
                BedrockPacketType.REQUEST_NETWORK_SETTINGS,
                RequestNetworkSettingsPacketProcessor::new
        );
        registry.freeze();
        var holder = new PacketProcessorHolder(registry);
        holder.setClientState(ClientState.CONNECTED);
        return holder;
    }

    public void tick(long currentTick) {
        // Send commands only once in one second to prevent lagging the client
        if (this.shouldSendCommands && currentTick % 20 == 0) {
            this.sendCommands();
            this.shouldSendCommands = false;
        }

        if (this.shouldSendHudElements) {
            this.sendHudElements();
            this.shouldSendHudElements = false;
        }

        if (this.shouldSendAbilities &&
            this.controlledEntity != null &&
            getClientState().ordinal() >= ClientState.SPAWNED.ordinal()) {
            broadcastPlayerAbilities();
            this.shouldSendAbilities = false;
        }
    }

    public void handlePacketSync(BedrockPacket packet, long receiveTime) {
        var selectedSession = protocolSession;
        if (selectedSession == null) {
            log.debug("Ignoring sync packet before protocol negotiation: {}", packet);
            return;
        }
        selectedSession.handleSync(this, packet, receiveTime);
    }

    protected byte assignContainerId() {
        if (containerIdCounter + 1 >= 100) {
            containerIdCounter = 1;
        }

        return containerIdCounter++;
    }

    protected int assignFormId() {
        return this.formIdCounter.getAndIncrement();
    }

    protected void onFullyJoin() {
        var server = Server.getInstance();
        var playerManager = server.getPlayerManager();
        var world = this.controlledEntity.getWorld();

        this.controlledEntity.loadNBT(playerManager.getPlayerStorage().readPlayerData(this).getNbt());

        viewEntityState(this.controlledEntity);
        viewPlayerGameMode(this.controlledEntity);
        this.controlledEntity.forEachViewers(viewer -> viewer.viewPlayerGameMode(this.controlledEntity));
        viewTime(world.getWorldData().getTimeOfDay());
        viewWeather(world.getWeather());
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.INVENTORY));
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.OFFHAND));
        viewContainerContents(this.controlledEntity.getContainer(ContainerTypes.ARMOR));
        viewPlayerAbilities(this);
        viewPlayerListChange(playerManager.getPlayers().values(), true);
        playerManager.getPlayers().values().stream()
                .filter(player -> player != this && player.getControlledEntity() != null)
                .forEach(this::viewPlayerAbilities);
        broadcastPlayerAbilities();

        this.controlledEntity.forEachViewers(viewer -> {
            viewer.viewEntityArmors(this.controlledEntity);
            viewer.viewEntityHand(this.controlledEntity);
            viewer.viewEntityOffhand(this.controlledEntity);
        });

        sendSpeed(this.speed);
        sendExperienceLevel(this.controlledEntity.getExperienceLevel());
        sendExperienceProgress(this.controlledEntity.getExperienceProgress());
        sendFoodLevel(this.controlledEntity.getFoodLevel());
        sendFoodSaturationLevel(this.controlledEntity.getFoodSaturationLevel());
        sendFoodExhaustionLevel(this.controlledEntity.getFoodExhaustionLevel());

        // Save player data the first time it joins
        playerManager.getPlayerStorage().savePlayerData(this);

        sendPlayStatus(PlayStatusPacket.Status.PLAYER_SPAWN);
    }

    @Override
    public void viewEntity(Entity entity) {
        sendPacket(getProtocol().getEncoder().encodeEntitySpawn(entity));
        viewPrimitiveShapes(entity.getAttachedPrimitiveShapes());
    }

    @Override
    public void removeEntity(Entity entity) {
        removePrimitiveShapes(entity.getAttachedPrimitiveShapes());
        sendPacket(getProtocol().getEncoder().encodeEntityRemove(entity));
    }

    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
        boolean self = this.controlledEntity == player;
        sendPacket(getProtocol().getEncoder().encodePlayerGameMode(player, self));
        if (self) {
            sendAdventureSettings();
        }
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3dc newLocation, boolean teleporting) {
        sendPacket(getProtocol().getEncoder().encodeEntityLocation(
                entity,
                newLocation,
                teleporting,
                this.controlledEntity == entity
        ));
    }

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        sendPacket(getProtocol().getEncoder().encodeEntityMotion(entity, motion));
    }

    @Override
    public void viewEntityState(Entity entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityState(entity));
    }

    protected EntityDataMap parseMetadata(Entity entity) {
        return getProtocol().getEncoder().encodeEntityMetadata(entity);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityHand(entity));
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityOffhand(entity));
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        sendPacket(getProtocol().getEncoder().encodeEntityArmor(entity));
    }

    @Override
    public void viewEntityAnimation(Entity entity, EntityAnimation animation) {
        sendPacket(getProtocol().getEncoder().encodeEntityAnimation(entity, animation));
    }

    @Override
    public void viewSleepingIndicator(int sleepingCount, int totalCount) {
        sendPacket(getProtocol().getEncoder().encodeSleepingIndicator(sleepingCount, totalCount));
    }

    @Override
    public void viewCommandBlockEditor(Vector3ic pos) {
        sendPacket(getProtocol().getEncoder().encodeCommandBlockEditor(pos));
    }

    @Override
    public void viewEnchantOptions(List<Pair<Integer, EnchantOption>> enchantOptions) {
        sendPacket(getProtocol().getEncoder().encodeEnchantOptions(enchantOptions));
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        var skin = player.getSkin();
        if (skin == null) {
            return;
        }

        boolean trustSkin = AllayServer.getSettings().resourcePackSettings().trustAllSkins();
        sendPackets(getProtocol().getEncoder().encodePlayerSkin(player, trustSkin));
        sendPackets(getProtocol().getEncoder().encodeSkinConfirmation(player, skin));
    }

    @Override
    public void viewEntityAction(Entity entity, EntityAction action) {
        sendPackets(getProtocol().getEncoder().encodeEntityAction(
                entity,
                action,
                this.controlledEntity == entity
        ));
    }

    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId, boolean silence) {
        sendPacket(getProtocol().getEncoder().encodePlayerEmote(player, emoteId, silence));
    }

    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
        sendPacket(getProtocol().getEncoder().encodeMobEffect(
                entity,
                newEffect,
                oldEffect,
                Server.getInstance().getTick()
        ));
    }

    @Override
    public void viewChunk(Chunk chunk) {
        sendPacket(createNetworkChunkPublisherUpdatePacket());
        sendPacket(createLevelChunkPacket(chunk));
        // This method will be called in a non-ticking thread if async chunk sending is enabled. Let's
        // send the entities in this chunk to the player next tick in the main thread: use forEachEntitiesInChunk()
        // instead of forEachEntitiesInChunkImmediately()
        this.controlledEntity.getDimension().getEntityManager().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(this));
        if (fullyJoinChunkThreshold.get() > 0 && fullyJoinChunkThreshold.decrementAndGet() == 0) {
            onFullyJoin();
        }
    }

    protected NetworkChunkPublisherUpdatePacket createNetworkChunkPublisherUpdatePacket() {
        return getProtocol().getEncoder().encodeChunkPublisher(this.controlledEntity);
    }

    protected LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        ChunkCache cache = null;
        UUID playerId = null;
        int cacheGen = 0;

        if (isCacheEffectivelyEnabled()) {
            cache = ChunkCache.getInstance();
            playerId = this.loginData.getUuid();
            cacheGen = cache.getGeneration(playerId);
        }

        return getProtocol().getEncoder().encodeLevelChunk(
                chunk,
                AllayServer.getSettings().worldSettings().useSubChunkSendingSystem(),
                cache,
                playerId,
                cacheGen
        );
    }

    public boolean isCacheEffectivelyEnabled() {
        return this.clientCacheEnabled && AllayServer.getSettings().networkSettings().enableClientChunkCache();
    }

    @Override
    public void removeChunk(long chunkHash) {
        this.controlledEntity.getDimension().getEntityManager().forEachEntitiesInChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash),
                entity -> entity.despawnFrom(this)
        );
    }

    @Override
    public void viewTime(int timeOfDay) {
        sendPacket(getProtocol().getEncoder().encodeTime(timeOfDay));
    }

    @Override
    public void viewGameRules(GameRules gameRules) {
        sendPacket(getProtocol().getEncoder().encodeGameRules(gameRules));
    }

    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
        sendPacket(getProtocol().getEncoder().encodeBlockUpdate(pos, layer, blockState));
    }

    @Override
    public void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates) {
        sendPackets(getProtocol().getEncoder().encodeBlockUpdates(
                chunk,
                blockUpdates,
                extraBlockUpdates
        ));
    }

    @Override
    public void viewBlockAction(Vector3ic p, BlockAction action) {
        sendPackets(getProtocol().getEncoder().encodeBlockAction(p, action));
    }

    @Override
    public void viewSound(Sound sound, Vector3dc p, boolean relative) {
        sendPackets(getProtocol().getEncoder().encodeSound(sound, p, relative));
    }

    @Override
    public void stopSound(String soundName) {
        sendPacket(getProtocol().getEncoder().encodeStopSound(soundName));
    }

    @Override
    public void viewParticle(Particle particle, Vector3dc p) {
        sendPackets(getProtocol().getEncoder().encodeParticle(
                particle,
                p,
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    @Override
    public void viewWeather(Weather weather) {
        sendPackets(getProtocol().getEncoder().encodeWeather(weather));
    }

    @Override
    public void viewPrimitiveShape(PrimitiveShape primitiveShape) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapes(
                List.of(primitiveShape),
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    @Override
    public void viewPrimitiveShapes(Set<PrimitiveShape> primitiveShapes) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapes(
                primitiveShapes,
                this.controlledEntity.getDimension().getDimensionType().getId()
        ));
    }

    @Override
    public void removePrimitiveShape(PrimitiveShape primitiveShape) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapeRemovals(List.of(primitiveShape)));
    }

    @Override
    public void removePrimitiveShapes(Set<PrimitiveShape> primitiveShapes) {
        sendPackets(getProtocol().getEncoder().encodePrimitiveShapeRemovals(primitiveShapes));
    }

    @Override
    public void viewBlockEntity(BlockEntity blockEntity) {
        if (!((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).sendToClient()) {
            return;
        }
        sendPacket(getProtocol().getEncoder().encodeBlockEntity(blockEntity));
    }

    @Override
    public void viewLectern(Vector3ic pos) {
        sendPacket(getProtocol().getEncoder().encodeLectern(pos));
    }

    @Override
    public void viewSignEditor(Vector3ic pos, boolean frontSide) {
        sendPacket(getProtocol().getEncoder().encodeSignEditor(pos, frontSide));
    }

    @Override
    public void viewContainerContents(Container container) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
            viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewContentsWithSpecificContainerId(container, id);
    }

    protected void viewContentsWithSpecificContainerId(Container container, int containerId) {
        sendPacket(getProtocol().getEncoder().encodeContainerContents(container, containerId));
    }

    @Override
    public void viewContainerSlot(Container container, int slot) {
        if (container instanceof AbstractPlayerContainer playerContainer) {
            if (playerContainer.getContainerType() == ContainerTypes.OFFHAND) {
                // HACK: for unknown reason, we should send InventoryContentPacket instead of InventorySlotPacket
                // for offhand container, otherwise the client will not update the offhand item
                // TODO: replace this hack when we find the reason and have better solution
                viewContentsWithSpecificContainerId(playerContainer, playerContainer.getUnopenedContainerId());
            } else {
                viewSlotWithSpecificContainerId(playerContainer, slot, playerContainer.getUnopenedContainerId());
            }
            return;
        }

        var id = idToContainer.inverse().get(container);
        if (id == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        viewSlotWithSpecificContainerId(container, slot, id);
    }

    protected void viewSlotWithSpecificContainerId(Container container, int slot, int containerId) {
        sendPacket(getProtocol().getEncoder().encodeContainerSlot(container, slot, containerId));
    }

    @Override
    public byte viewContainerOpen(Container container) {
        if (idToContainer.inverse().containsKey(container)) {
            throw new IllegalStateException("The container " + container.getContainerType() + " have been opened by this viewer");
        }

        var assignedId = assignContainerId();
        sendContainerOpenPacket(assignedId, container);
        registerOpenedContainer(assignedId, container);

        var containerType = container.getContainerType();
        // We should send the container's contents to the client if the container is not held by the entity
        if (this.controlledEntity.getContainer(containerType) == null) {
            viewContainerContents(container);
        }

        return assignedId;
    }

    protected void registerOpenedContainer(byte assignedId, Container container) {
        this.idToContainer.put(assignedId, container);
        this.typeToContainer.put(container.getContainerType(), container);
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.put(slotType, container.getContainerType()));
    }

    @SneakyThrows
    protected void sendContainerOpenPacket(byte assignedId, Container container) {
        Vector3ic position = switch (container) {
            case BlockContainer blockContainer -> blockContainer.getBlockPos();
            case FakeContainerImpl fakeContainer -> fakeContainer.getFakeBlockPos(this);
            default -> {
                var location = this.controlledEntity.getLocation();
                yield new org.joml.Vector3i(
                        (int) Math.floor(location.x()),
                        (int) Math.floor(location.y()),
                        (int) Math.floor(location.z())
                );
            }
        };
        sendPacket(getProtocol().getEncoder().encodeContainerOpen(container, assignedId, position));
    }

    @Override
    public void viewContainerClose(Container container) {
        var assignedId = this.idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("Trying to close a container which is not opened! Type: " + container.getContainerType());
        }

        sendContainerClosePacket(assignedId, container);
        unregisterOpenedContainer(assignedId, container);
    }

    protected void unregisterOpenedContainer(byte assignedId, Container container) {
        this.typeToContainer.remove(Objects.requireNonNull(idToContainer.remove(assignedId)).getContainerType());
        ContainerNetworkInfo.getInfo(container.getContainerType()).heldSlotTypes().forEach(slotType -> slotTypeToFullType.remove(slotType));
    }

    @SneakyThrows
    protected void sendContainerClosePacket(byte assignedId, Container container) {
        if (this.suppressNextContainerClosePacket) {
            this.suppressNextContainerClosePacket = false;
            this.containerClosedByClient = false;
            return;
        }

        // This flag must identify server-forced closes or the client may reject the packet.
        boolean serverInitiated = !this.containerClosedByClient;
        this.containerClosedByClient = false;
        sendPacket(getProtocol().getEncoder().encodeContainerClose(
                container,
                assignedId,
                serverInitiated
        ));
    }

    @Override
    public void viewContainerData(Container container, int property, int value) {
        var assignedId = idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        sendPacket(getProtocol().getEncoder().encodeContainerData(assignedId, property, value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> T getOpenedContainer(ContainerType<T> type) {
        // Special case: If the player opens their inventory, they also implicitly open a series of other
        // containers, even if not registered
        Container container = null;
        if (isPlayerInventoryOpened()) {
            if (type == ContainerTypes.ARMOR || type == ContainerTypes.OFFHAND || type == ContainerTypes.CRAFTING_GRID) {
                container = this.controlledEntity.getContainer(type);
            }
        }

        if (container == null) {
            container = typeToContainer.get(type);
        }

        return (T) container;
    }

    @SuppressWarnings("unchecked")
    public <T extends Container> T getOpenedContainer(ContainerSlotType slotType) {
        // Similarly, special case handling needed
        ContainerType<?> type = null;
        if (isPlayerInventoryOpened()) {
            type = switch (slotType) {
                case ARMOR -> ContainerTypes.ARMOR;
                case OFFHAND -> ContainerTypes.OFFHAND;
                case CRAFTING_INPUT -> ContainerTypes.CRAFTING_GRID;
                default -> null;
            };
        }

        if (type == null) {
            type = slotTypeToFullType.get(slotType);
        }
        return (T) getOpenedContainer(type);
    }

    protected boolean isPlayerInventoryOpened() {
        return typeToContainer.get(ContainerTypes.INVENTORY) != null;
    }

    @Override
    public Container getOpenedContainer(byte id) {
        return idToContainer.get(id);
    }

    @Override
    public Set<Container> getOpenedContainers() {
        var containers = new HashSet<>(this.idToContainer.values());

        // Similarly, special case handling needed
        if (isPlayerInventoryOpened()) {
            containers.add(getOpenedContainer(ContainerTypes.ARMOR));
            containers.add(getOpenedContainer(ContainerTypes.OFFHAND));
            containers.add(getOpenedContainer(ContainerTypes.CRAFTING_GRID));
        }

        return containers;
    }

    @Override
    public void closeAllOpenedContainers() {
        for (var container : getOpenedContainers()) {
            container.removeViewer(this);
        }
    }

    @Override
    public void viewBossBar(BossBar bossBar) {
        // Clear any existing boss bar client-side before sending the new one. Although we
        // can use BossEventPacket.Action.UPDATE_XXX to update the existing one, it is much
        // easier to implement by creating a new one.
        clearBossBar();
        sendPacket(getProtocol().getEncoder().encodeBossBar(
                this.controlledEntity.getUniqueId().getLeastSignificantBits(),
                bossBar
        ));
    }

    @Override
    public void clearBossBar() {
        sendPacket(getProtocol().getEncoder().encodeBossBarRemoval(
                this.controlledEntity.getUniqueId().getLeastSignificantBits()
        ));
    }

    @Override
    public int setServerSettingForm(CustomForm form) {
        var id = assignFormId();
        this.serverSettingForm = new Pair<>(id, form);
        return id;
    }

    @Override
    public Pair<Integer, CustomForm> removeServerSettingForm() {
        var tmp = this.serverSettingForm;
        this.serverSettingForm = null;
        return tmp;
    }

    @Override
    public int viewForm(Form form) {
        var id = assignFormId();
        this.forms.put(id, form);

        sendPacket(getProtocol().getEncoder().encodeForm(id, form));

        return id;
    }

    @Override
    public @UnmodifiableView Map<Integer, Form> getForms() {
        return Collections.unmodifiableMap(this.forms.asMap());
    }

    @Override
    public Form removeForm(int formId) {
        var form = this.forms.getIfPresent(formId);
        if (form != null) {
            this.forms.invalidate(formId);
        }

        return form;
    }

    @Override
    public void closeAllForms() {
        sendPacket(getProtocol().getEncoder().encodeCloseForms());
        this.forms.invalidateAll();
    }

    @Override
    public DDUIScreenSession viewScreen(DDUIScreen screen) {
        if (this.activeScreen != null) {
            this.activeScreen.close();
        }

        var session = new AllayDDUIScreenSession(this, assignFormId(), screen);
        this.activeScreen = session;
        session.show();
        return session;
    }

    @Override
    public DDUIScreenSession getActiveScreen() {
        return this.activeScreen;
    }

    @Override
    public DDUIScreenSession removeActiveScreen() {
        var tmp = this.activeScreen;
        this.activeScreen = null;
        return tmp;
    }

    @Override
    public void closeScreen() {
        if (this.activeScreen instanceof AllayDDUIScreenSession screenSession) {
            screenSession.close();
        } else if (this.activeScreen != null) {
            this.activeScreen.close();
        }
    }

    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        // Client won't storage the score of a scoreboard,so we should send the score to client
        sendPackets(getProtocol().getEncoder().encodeScoreboard(
                scoreboard,
                slot,
                scoreboard.getLines().values()
                        .stream()
                        .map(this::toNetworkScoreInfo)
                        .filter(Objects::nonNull)
                        .toList()
        ));

        var scorer = new PlayerScorer(this);
        var line = scoreboard.getLine(scorer);
        if (slot == DisplaySlot.BELOW_NAME && line != null) {
            this.controlledEntity.setScoreTag(line.getScore() + " " + scoreboard.getDisplayName());
        }
    }

    protected ScoreInfo toNetworkScoreInfo(ScoreboardLine line) {
        var scoreboard = line.getScoreboard();
        switch (line.getScorer()) {
            case EntityScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.ENTITY, scorer.getUniqueId()
                );
            }
            case PlayerScorer scorer -> {
                if (scorer.getUuid() == null) {
                    return null;
                }

                var player = Server.getInstance().getPlayerManager().getPlayers().get(scorer.getUuid());
                if (player == null) {
                    return null;
                }

                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(), line.getScore(),
                        ScoreInfo.ScorerType.PLAYER, this.controlledEntity.getRuntimeId()
                );
            }
            case FakeScorer scorer -> {
                return new ScoreInfo(
                        line.getLineId(), scoreboard.getObjectiveName(),
                        line.getScore(), scorer.getFakeName()
                );
            }
        }
    }

    @Override
    public void hideScoreboardSlot(DisplaySlot slot) {
        sendPacket(getProtocol().getEncoder().encodeScoreboardSlotRemoval(slot));

        if (slot == DisplaySlot.BELOW_NAME) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        sendPacket(getProtocol().getEncoder().encodeScoreboardRemoval(scoreboard));
    }

    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        sendPacket(getProtocol().getEncoder().encodeScore(
                SetScorePacket.Action.REMOVE,
                toNetworkScoreInfo(line)
        ));

        var scorer = new PlayerScorer(this);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    @Override
    public void updateScore(ScoreboardLine line) {
        sendPacket(getProtocol().getEncoder().encodeScore(
                SetScorePacket.Action.SET,
                toNetworkScoreInfo(line)
        ));

        var scorer = new PlayerScorer(this);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.controlledEntity.setScoreTag(line.getScore() + " " + line.getScoreboard().getDisplayName());
        }
    }

    @Override
    public boolean isScoreboardViewerValid() {
        return !isDisconnected();
    }

    @Override
    public void sendExperienceLevel(int value) {
        sendAttribute(new AttributeData("minecraft:player.level", 0, Float.MAX_VALUE, value));
    }

    @Override
    public void sendExperienceProgress(float value) {
        sendAttribute(new AttributeData("minecraft:player.experience", 0, 1, value));
    }

    @Override
    public void sendFoodLevel(int value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.hunger", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    @Override
    public void sendFoodSaturationLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_SATURATION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.saturation", 0, max,
                value, 0, max, max, Collections.emptyList()
        ));
    }

    @Override
    public void sendFoodExhaustionLevel(float value) {
        var max = EntityPlayerBaseComponent.MAX_FOOD_EXHAUSTION_LEVEL;
        sendAttribute(new AttributeData(
                "minecraft:player.exhaustion", 0, max,
                value, 0, max, 0, Collections.emptyList()
        ));
    }

    @Override
    public void sendHealth(float health, float maxHealth) {
        var defaultMax = EntityLivingComponent.DEFAULT_MAX_HEALTH;
        sendAttribute(new AttributeData(
                "minecraft:health", 0, maxHealth,
                health, 0, defaultMax, defaultMax,
                Collections.emptyList()
        ));
    }

    @Override
    public void sendAbsorption(float absorption) {
        var maxAbsorption = 16.0f;
        sendAttribute(new AttributeData(
                "minecraft:absorption", 0, maxAbsorption,
                absorption, 0, maxAbsorption, 0,
                Collections.emptyList()
        ));
    }

    @Override
    public void sendCooldown(String category, int duration) {
        sendPacket(getProtocol().getEncoder().encodeCooldown(category, duration));
    }

    @Override
    public void sendMapData(long mapId, BufferedImage image) {
        sendPacket(getProtocol().getEncoder().encodeMapData(mapId, image));
    }

    @Override
    public void sendDeathInfo(Pair<String, String[]> deathInfo) {
        sendPacket(getProtocol().getEncoder().encodeDeathInfo(
                I18n.get().tr(this.loginData.getLangCode(), deathInfo.left(), (Object[]) deathInfo.right())
        ));
    }

    @Override
    public void sendItemChargingFinished() {
        sendPacket(getProtocol().getEncoder().encodeItemChargingFinished(
                this.controlledEntity.getRuntimeId()
        ));
    }

    protected void sendAttribute(AttributeData attributeData) {
        sendPacket(getProtocol().getEncoder().encodeAttribute(
                this.controlledEntity.getRuntimeId(),
                attributeData
        ));
    }

    @Override
    public void sendTip(String message) {
        sendSimpleMessage(message, TextPacket.Type.TIP);
    }

    @Override
    public void sendPopup(String message) {
        sendSimpleMessage(message, TextPacket.Type.POPUP);
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        sendMessage(I18n.get().tr(this.loginData.getLangCode(), translatable, args));
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        if (!this.controlledEntity.hasPermissions(permissions)) {
            return;
        }

        if (sender == this.controlledEntity) {
            // NOTICE: Sending too long text via CommandOutputPacket will make the client disconnect in
            // 1.21.130+, let just use TextPacket here since it's probably no different :c
            for (var output : outputs) {
                sendMessage(I18n.get().tr(this.loginData.getLangCode(), output.str(), output.args()));
            }
        } else if (this.controlledEntity.hasPermission(Permissions.COMMAND_VIEW_OTHER_OUTPUTS).asBoolean()) {
            for (var output : outputs) {
                var str = TextFormat.GRAY + "" + TextFormat.ITALIC + "[" + sender.getCommandSenderName() + ": " + I18n.get().tr(this.loginData.getLangCode(), output.str(), output.args()) + "]";
                sendMessage(str);
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        sendSimpleMessage(message, TextPacket.Type.RAW);
    }

    protected void sendSimpleMessage(String message, TextPacket.Type type) {
        sendPacket(getProtocol().getEncoder().encodeText(this.loginData.getXuid(), message, type));
    }

    @Override
    public void sendToast(String title, String content) {
        sendPacket(getProtocol().getEncoder().encodeToast(title, content));
    }

    @Override
    public void sendTitle(String title) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.TITLE, title, 0, 0, 0));
    }

    @Override
    public void sendSubtitle(String subtitle) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.SUBTITLE, subtitle, 0, 0, 0));
    }

    @Override
    public void sendActionBar(String actionBar) {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.ACTIONBAR, actionBar, 0, 0, 0));
    }

    @Override
    public void setTitleSettings(int fadeInTime, int duration, int fadeOutTime) {
        sendPacket(getProtocol().getEncoder().encodeTitle(
                SetTitlePacket.Type.TIMES,
                null,
                fadeInTime,
                duration,
                fadeOutTime
        ));
    }

    @Override
    public void resetTitleSettings() {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.RESET, null, 0, 0, 0));
    }

    @Override
    public void clearTitle() {
        sendPacket(getProtocol().getEncoder().encodeTitle(SetTitlePacket.Type.CLEAR, null, 0, 0, 0));
    }

    @Override
    public void shakeCamera(CameraShakeType shakeType, float intensity, float duration) {
        sendPacket(getProtocol().getEncoder().encodeCameraShake(shakeType, intensity, duration));
    }

    @Override
    public void stopCameraShake() {
        sendPacket(getProtocol().getEncoder().encodeCameraShakeStop());
    }

    @Override
    public void pushFog(String fogId) {
        fogStack.add(fogId);
        sendFogStack();
    }

    @Override
    public boolean popFog(String fogId) {
        for (int i = fogStack.size() - 1; i >= 0; i--) {
            if (fogStack.get(i).equals(fogId)) {
                fogStack.remove(i);
                sendFogStack();
                return true;
            }
        }
        return false;
    }

    @Override
    public int removeFog(String fogId) {
        int count = 0;
        var iterator = fogStack.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(fogId)) {
                iterator.remove();
                count++;
            }
        }
        if (count > 0) {
            sendFogStack();
        }
        return count;
    }

    @Override
    public List<String> getFogs() {
        return Collections.unmodifiableList(fogStack);
    }

    @Override
    public void removeAllFogs() {
        if (!fogStack.isEmpty()) {
            fogStack.clear();
            sendFogStack();
        }
    }

    protected void sendFogStack() {
        sendPacket(getProtocol().getEncoder().encodeFogStack(fogStack));
    }

    @Override
    public void beginDimensionChange(DimensionType targetDimType, double x, double y, double z) {
        this.changingDimension = true;
        sendPacket(getProtocol().getEncoder().encodeDimensionChange(targetDimType, x, y, z));
    }

    @Override
    public void completeDimensionChange() {
        this.changingDimension = false;

        // As of v1.19.50, the dimension ack that is meant to be sent by the client is now sent by the server.
        // Send it after the player has been added to the target dimension.
        sendPacket(getProtocol().getEncoder().encodeDimensionChangeSuccess(
                this.controlledEntity.getRuntimeId()
        ));
    }

    public void sendPacket(BedrockPacket packet) {
        if (packet == null) {
            return;
        }
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.send(this, packet);
        } else {
            sendBootstrapPacket(packet, false);
        }
    }

    public void sendPackets(Collection<? extends BedrockPacket> packets) {
        if (packets == null || packets.isEmpty()) {
            return;
        }
        packets.forEach(packet ->
                Objects.requireNonNull(packet, "packets contains null")
        );
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.send(this, packets);
        } else {
            packets.forEach(packet -> sendBootstrapPacket(packet, false));
        }
    }

    public void sendPacketImmediately(BedrockPacket packet) {
        if (packet == null) {
            return;
        }
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.sendImmediately(this, packet);
        } else {
            sendBootstrapPacket(packet, true);
        }
    }

    public void sendPacketsImmediately(Collection<? extends BedrockPacket> packets) {
        if (packets == null || packets.isEmpty()) {
            return;
        }
        packets.forEach(packet ->
                Objects.requireNonNull(packet, "packets contains null")
        );
        var selectedSession = protocolSession;
        if (selectedSession != null) {
            selectedSession.sendImmediately(this, packets);
        } else {
            packets.forEach(packet -> sendBootstrapPacket(packet, true));
        }
    }

    private void sendBootstrapPacket(BedrockPacket packet, boolean immediately) {
        Objects.requireNonNull(packet, "packet");
        if (!(packet instanceof PlayStatusPacket)) {
            throw new IllegalStateException(
                    "Only PlayStatusPacket may be sent before protocol negotiation: " + packet.getPacketType()
            );
        }
        if (!getClientState().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(this, packet);
        if (!event.call()) {
            return;
        }
        var outgoingPacket = event.getPacket();
        if (outgoingPacket == null) {
            throw new IllegalStateException("PacketSendEvent replaced a bootstrap packet with null");
        }
        if (!(outgoingPacket instanceof PlayStatusPacket)) {
            throw new IllegalStateException(
                    "PacketSendEvent replaced a bootstrap packet with " + outgoingPacket.getPacketType()
            );
        }
        if (!getClientState().canHandlePackets() || !session.isConnected()) {
            return;
        }

        if (immediately) {
            this.session.sendPacketImmediately(outgoingPacket);
        } else {
            this.session.sendPacket(outgoingPacket);
        }
    }

    protected CommandData encodeCommand(Command command) {
        // Aliases
        CommandEnumData aliases = null;
        if (!command.getAliases().isEmpty()) {
            var values = new LinkedHashMap<String, Set<CommandEnumConstraint>>();
            command.getAliases().forEach(alias -> values.put(alias, Collections.emptySet()));
            values.put(command.getName(), Collections.emptySet());
            aliases = new CommandEnumData(command.getName() + "CommandAliases", values, false);
        }

        // Overloads
        var overloads = new ArrayList<CommandOverloadData>();
        for (var leaf : command.getCommandTree().getLeaves()) {
            var params = new CommandParamData[leaf.depth()];

            var hasPermission = true;
            var node = leaf;
            var index = leaf.depth() - 1;
            while (!node.isRoot()) {
                if (!this.controlledEntity.hasPermissions(node.getPermissions())) {
                    hasPermission = false;
                    break;
                }

                params[index] = ((BaseNode) node).toNetworkData();
                node = node.parent();
                index--;
            }

            if (hasPermission) {
                overloads.add(new CommandOverloadData(false, params));
            }
        }
        if (overloads.isEmpty()) {
            overloads.add(new CommandOverloadData(false, new CommandParamData[0]));
        }

        // Flags
        var flags = new HashSet<CommandData.Flag>();
        flags.add(CommandData.Flag.NOT_CHEAT);
        if (command.isDebugCommand()) {
            flags.add(CommandData.Flag.TEST_USAGE);
        }

        return new CommandData(
                command.getName(), I18n.get().tr(this.loginData.getLangCode(), command.getDescription()),
                flags, CommandPermission.ANY, aliases, overloads.toArray(CommandOverloadData[]::new), List.of()
        );
    }

    @Override
    public void disconnect(@MayContainTrKey String reason) {
        ClientState lastClientState;
        synchronized (protocolLifecycleLock) {
            var processorHolder = activeProcessorHolder();
            if (!processorHolder.setClientState(ClientState.DISCONNECTED)) {
                log.warn("Trying to disconnect a player who is already disconnected!");
                return;
            }
            lastClientState = processorHolder.getLastClientState();
        }

        String translatedReason;
        if (lastClientState.ordinal() >= ClientState.LOGGED_IN.ordinal()) {
            translatedReason = I18n.get().tr(this.loginData.getLangCode(), reason);
        } else {
            // At that time login data is null
            translatedReason = I18n.get().tr(reason);
        }

        try {
            onDisconnect(translatedReason);
            // Tell the client that it should disconnect
            if (this.session.isConnected()) {
                this.session.disconnect(translatedReason);
            }
        } catch (Throwable t) {
            log.error("Error while disconnecting the session", t);
        }
    }

    @Override
    public ClientState getClientState() {
        return activeProcessorHolder().getClientState();
    }

    public void setClientState(ClientState state) {
        synchronized (protocolLifecycleLock) {
            activeProcessorHolder().setClientState(state);
        }
    }

    @Override
    public ClientState getLastClientState() {
        return activeProcessorHolder().getLastClientState();
    }

    public boolean installProtocol(Protocol protocol) {
        Objects.requireNonNull(protocol, "protocol");
        synchronized (protocolLifecycleLock) {
            if (protocolSession != null) {
                throw new IllegalStateException("A protocol is already installed for this connection");
            }
            if (bootstrapProcessorHolder.getClientState() != ClientState.CONNECTED || !session.isConnected()) {
                return false;
            }

            var selectedSession = new ProtocolSession(protocol, session);
            selectedSession.installCodec();
            protocolSession = selectedSession;
            return true;
        }
    }

    public Protocol getProtocol() {
        var selectedSession = protocolSession;
        if (selectedSession == null) {
            throw new IllegalStateException("Protocol negotiation has not completed");
        }
        return selectedSession.getProtocol();
    }

    private PacketProcessorHolder activeProcessorHolder() {
        var selectedSession = protocolSession;
        return selectedSession == null ? bootstrapProcessorHolder : selectedSession.getProcessorHolder();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return this.session.getSocketAddress();
    }

    protected void onDisconnect(String disconnectReason) {
        var activeScreen = removeActiveScreen();
        if (activeScreen instanceof AllayDDUIScreenSession session) {
            session.discard();
        }
        new PlayerDisconnectEvent(this, disconnectReason).call();
        closeAllOpenedContainers();
        if (getLastClientState().ordinal() >= ClientState.SPAWNED.ordinal()) {
            ChunkCache.getInstance().removePlayer(this.loginData.getUuid());
        }
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).removePlayer(this);
    }

    @Override
    public int getPing() {
        return sourceInterface.getPing(session);
    }

    @Override
    public void viewPlayerAbilities(Player player) {
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        sendPacket(getProtocol().getEncoder().encodePlayerAbilities(
                entity.getUniqueId().getLeastSignificantBits(),
                entity.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR).asBoolean()
                        ? CommandPermission.GAME_DIRECTORS
                        : CommandPermission.ANY,
                calculatePlayerPermission(player),
                calculateAbilities(player),
                (float) DEFAULT_SPEED.calculate(),
                (float) player.getFlySpeed().calculate(),
                (float) player.getVerticalFlySpeed().calculate()
        ));

        if (player == this) {
            sendAdventureSettings();
            this.shouldSendCommands = true;
            this.shouldSendAbilities = false;
        }
    }

    protected void broadcastPlayerAbilities() {
        if (this.controlledEntity == null) {
            return;
        }

        viewPlayerAbilities(this);
        this.controlledEntity.forEachViewers(viewer -> {
            if (viewer instanceof Player playerViewer && playerViewer != this) {
                playerViewer.viewPlayerAbilities(this);
            }
        });
    }

    protected EnumSet<Ability> calculateAbilities(Player player) {
        var abilities = EnumSet.noneOf(Ability.class);
        for (var ability : player.getAbilities()) {
            abilities.add(toNetworkAbility(ability));
        }

        if (player.getControlledEntity() != null && player.getControlledEntity().hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR).asBoolean()) {
            abilities.add(Ability.OPERATOR_COMMANDS);
        }

        if (!player.canFly()) {
            abilities.remove(Ability.MAY_FLY);
            abilities.remove(Ability.FLYING);
        }

        if (player.isAlwaysFlying()) {
            abilities.add(Ability.MAY_FLY);
            abilities.add(Ability.FLYING);
        }

        return abilities;
    }

    protected PlayerPermission calculatePlayerPermission(Player player) {
        var build = player.canPlaceBlocks();
        var mine = player.canBreakBlocks();
        var doorsAndSwitches = player.canInteractWithBlocks();
        var openContainers = player.canOpenContainers();
        var attackPlayers = player.canAttackPlayers();
        var attackMobs = player.canAttackMobs();

        if (
            Server.getInstance().getPlayerManager().isOperator(player) &&
            build && mine && doorsAndSwitches && openContainers && attackPlayers && attackMobs
        ) {
            return PlayerPermission.OPERATOR;
        }

        if (build && mine && doorsAndSwitches && openContainers && attackPlayers && attackMobs) {
            return PlayerPermission.MEMBER;
        }

        if (!build && !mine && !doorsAndSwitches && !openContainers && !attackPlayers && !attackMobs) {
            return PlayerPermission.VISITOR;
        }

        return PlayerPermission.CUSTOM;
    }

    protected void sendAdventureSettings() {
        if (this.controlledEntity == null || getClientState().ordinal() < ClientState.SPAWNED.ordinal()) {
            return;
        }

        sendPacket(getProtocol().getEncoder().encodeAdventureSettings(
                !canAttackMobs(),
                !canAttackMobs(),
                this.controlledEntity.getGameMode() != GameMode.SPECTATOR,
                isImmutableWorld()
        ));
    }

    protected static EnumSet<PlayerAbility> createBaseAbilitySet() {
        return EnumSet.noneOf(PlayerAbility.class);
    }

    protected static EnumSet<PlayerAbility> abilitiesFromPermission(PlayerPermission permission) {
        var abilities = createBaseAbilitySet();
        switch (permission) {
            case OPERATOR -> abilities.addAll(EnumSet.of(
                    PlayerAbility.PLACE_BLOCK,
                    PlayerAbility.BREAK_BLOCK,
                    PlayerAbility.INTERACT_BLOCK,
                    PlayerAbility.OPEN_CONTAINER,
                    PlayerAbility.ATTACK_PLAYER,
                    PlayerAbility.ATTACK_MOB
            ));
            case MEMBER -> abilities.addAll(EnumSet.of(
                    PlayerAbility.PLACE_BLOCK,
                    PlayerAbility.BREAK_BLOCK,
                    PlayerAbility.INTERACT_BLOCK,
                    PlayerAbility.OPEN_CONTAINER,
                    PlayerAbility.ATTACK_PLAYER,
                    PlayerAbility.ATTACK_MOB
            ));
            case VISITOR, CUSTOM -> {
                // Keep only the common non-permission abilities.
            }
        }
        return abilities;
    }

    protected Ability toNetworkAbility(PlayerAbility ability) {
        return switch (ability) {
            case PLACE_BLOCK -> Ability.BUILD;
            case BREAK_BLOCK -> Ability.MINE;
            case INTERACT_BLOCK -> Ability.DOORS_AND_SWITCHES;
            case OPEN_CONTAINER -> Ability.OPEN_CONTAINERS;
            case ATTACK_PLAYER -> Ability.ATTACK_PLAYERS;
            case ATTACK_MOB -> Ability.ATTACK_MOBS;
            case FLYING -> Ability.FLYING;
            case MAY_FLY -> Ability.MAY_FLY;
            case INFINITE_BLOCK -> Ability.INSTABUILD;
            case NO_CLIP -> Ability.NO_CLIP;
        };
    }

    protected void scheduleAbilitiesUpdate(Set<PlayerAbility> previous) {
        if (this.controlledEntity != null) {
            new PlayerAbilitiesUpdateEvent(this, previous, this.abilities).call();
        }
        this.shouldSendAbilities = true;
    }

    @Override
    public @UnmodifiableView Set<PlayerAbility> getAbilities() {
        return Collections.unmodifiableSet(abilities);
    }

    @Override
    public boolean hasAbility(PlayerAbility ability) {
        return this.abilities.contains(ability);
    }

    @Override
    public void setAbility(PlayerAbility ability, boolean value) {
        if (this.abilities.contains(ability) == value) {
            return;
        }

        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        if (value) {
            this.abilities.add(ability);
        } else {
            this.abilities.remove(ability);
        }
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    @Override
    public void setAbilities(Set<PlayerAbility> abilities, boolean value) {
        if ((value && this.abilities.containsAll(abilities)) || (!value && Collections.disjoint(this.abilities, abilities))) {
            return;
        }

        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        if (value) {
            this.abilities.addAll(abilities);
        } else {
            this.abilities.removeAll(abilities);
        }
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    @Override
    public void setAbilities(Set<PlayerAbility> abilities) {
        if (this.abilities.equals(abilities)) {
            return;
        }

        var wasAlwaysFlying = isAlwaysFlying();
        var couldFly = canFly();

        var snap = EnumSet.copyOf(this.abilities);
        this.abilities.clear();
        this.abilities.addAll(abilities);
        scheduleAbilitiesUpdate(snap);

        syncFlyingState(wasAlwaysFlying, couldFly);
    }

    @Override
    public boolean canPlaceBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.PLACE_BLOCK);
    }

    @Override
    public boolean canBreakBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.ADVENTURE) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.BREAK_BLOCK);
    }

    @Override
    public boolean canInteractWithBlocks() {
        if (isImmutableWorld()) {
            return false;
        }
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.INTERACT_BLOCK);
    }

    @Override
    public boolean canOpenContainers() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.OPEN_CONTAINER);
    }

    @Override
    public boolean canAttackPlayers() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.ATTACK_PLAYER);
    }

    @Override
    public boolean canAttackMobs() {
        if (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return true;
        }
        return hasAbility(PlayerAbility.ATTACK_MOB);
    }

    @Override
    public boolean canFly() {
        return this.controlledEntity != null && (isAlwaysFlying() || hasAbility(PlayerAbility.MAY_FLY));
    }

    @Override
    public boolean hasInfiniteBlock() {
        return hasAbility(PlayerAbility.INFINITE_BLOCK);
    }

    @Override
    public boolean isNoClip() {
        return this.controlledEntity != null && (hasAbility(PlayerAbility.NO_CLIP) || this.controlledEntity.getGameMode() == GameMode.SPECTATOR);
    }

    @Override
    public boolean isImmutableWorld() {
        if (Server.getInstance().getPlayerManager().isOperator(this)) {
            return false;
        }

        if (this.immutableWorld) {
            return true;
        }

        if (this.controlledEntity != null) {
            return this.controlledEntity.getGameMode() == GameMode.SPECTATOR || this.controlledEntity.getGameMode() == GameMode.ADVENTURE;
        }

        return false;
    }

    @Override
    public void setImmutableWorld(boolean immutableWorld) {
        if (this.immutableWorld != immutableWorld) {
            this.immutableWorld = immutableWorld;
            this.shouldSendAbilities = true;
        }
    }

    @Override
    public boolean isAlwaysFlying() {
        return this.alwaysFlying || hasAbility(PlayerAbility.NO_CLIP) || (this.controlledEntity != null && this.controlledEntity.getGameMode() == GameMode.SPECTATOR);
    }

    @Override
    public void setAlwaysFlying(boolean alwaysFlying) {
        var wasAlwaysFlying = this.alwaysFlying;
        this.alwaysFlying = alwaysFlying;
        this.shouldSendAbilities = true;
        syncFlyingState(wasAlwaysFlying, canFly());
    }

    protected void syncFlyingState(boolean wasAlwaysFlying, boolean couldFly) {
        if (!wasAlwaysFlying && isAlwaysFlying()) {
            this.controlledEntity.setFlying(true);
        } else if (couldFly && !canFly()) {
            this.controlledEntity.setFlying(false);
        }
    }

    @Override
    public void viewPlayerListChange(Collection<Player> players, boolean add) {
        sendPacket(getProtocol().getEncoder().encodePlayerList(
                players,
                add,
                AllayServer.getSettings().resourcePackSettings().trustAllSkins()
        ));
    }

    @Override
    public void setSpeed(Speed speed) {
        if (!this.speed.equals(speed)) {
            this.speed = speed;
            sendSpeed(this.speed);
        }
    }

    protected void sendSpeed(Speed speed) {
        sendAttribute(new AttributeData(
                "minecraft:movement", 0, Float.MAX_VALUE, (float) speed.calculate(), 0,
                Float.MAX_VALUE, (float) DEFAULT_SPEED.calculate(), Collections.emptyList()
        ));
    }

    @Override
    public void setFlySpeed(Speed flySpeed) {
        if (!this.flySpeed.equals(flySpeed)) {
            this.flySpeed = flySpeed;
            viewPlayerAbilities(this);
        }
    }

    @Override
    public void setVerticalFlySpeed(Speed verticalFlySpeed) {
        if (!this.verticalFlySpeed.equals(verticalFlySpeed)) {
            this.verticalFlySpeed = verticalFlySpeed;
            viewPlayerAbilities(this);
        }
    }

    @Override
    public void setMotion(Vector3dc m) {
        sendPacket(getProtocol().getEncoder().encodeEntityMotion(this.controlledEntity, m));
    }

    @Override
    public void setHudElementVisibility(HudElement element, boolean visible) {
        if (visible == getHudElementVisibility(element)) {
            return;
        }

        if (!visible) {
            this.hiddenHudElements.add(element);
        } else {
            this.hiddenHudElements.remove(element);
        }

        this.shouldSendHudElements = true;
    }

    @Override
    public boolean getHudElementVisibility(HudElement element) {
        return !this.hiddenHudElements.contains(element);
    }

    @Override
    public void transfer(String ip, int port) {
        sendPacket(getProtocol().getEncoder().encodeTransfer(ip, port));
    }

    /**
     * Reads all the data in {@link PlayerData} except nbt. To be more exact, this method will validate and set
     * the player entity's current pos and then spawn it. The nbt will be used in EntityPlayer::loadNBT() later in
     * doFirstSpawn() method instead of here because some packets must be sent after the player fully joined the server.
     */
    public void spawnEntityPlayer() {
        var server = Server.getInstance();
        var playerManager = (AllayPlayerManager) server.getPlayerManager();
        var playerData = playerManager.getPlayerStorage().readPlayerData(this);

        // Validate and set player pos
        AllayDimension dimension;
        Vector3fc currentPos;

        var logOffWorld = server.getWorldPool().getWorld(playerData.getWorld());
        var logOffDimension = logOffWorld == null ? null : logOffWorld.getDimension(new Identifier(playerData.getDimension()));
        if (logOffDimension == null) {
            // The world or dimension where the player logged off doesn't exist, fallback to the global spawn point
            dimension = (AllayDimension) server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());
            var currentWorldName = dimension.getWorld().getWorldData().getDisplayName();
            var currentDimension = dimension.getDimensionType().getIdentifier().toString();

            // The old pos stored in player's nbt is invalid, and we should replace it with the new one!
            var builder = playerData.getNbt().toBuilder();
            writeVector3f(builder, "Pos", currentPos);
            playerData.setNbt(builder.build());
            playerData.setWorld(currentWorldName);
            playerData.setDimension(currentDimension);

            // Save new player data back to storage
            playerManager.getPlayerStorage().savePlayerData(this.loginData.getUuid(), playerData);
        } else {
            dimension = (AllayDimension) logOffDimension;
            currentPos = readVector3f(playerData.getNbt(), "Pos");
        }

        var storedAbilities = playerData.getAbilities();
        if (storedAbilities == null) {
            var permissionName = AllayServer.getSettings().genericSettings().defaultPermission().toUpperCase();
            this.abilities.addAll(abilitiesFromPermission(PlayerPermission.valueOf(permissionName)));

            var gameMode = GameMode.from(playerData.getNbt().getInt("PlayerGameMode", NetworkHelper.toNetwork(dimension.getWorld().getWorldData().getGameMode()).ordinal()));
            this.abilities.addAll(gameMode.getAbilities());
        } else {
            this.abilities.addAll(storedAbilities);
        }

        this.controlledEntity = EntityTypes.PLAYER.createEntity();
        this.controlledEntity.setSkin(this.loginData.getSkin());
        this.controlledEntity.setDisplayName(loginData.getXname());
        this.controlledEntity.setNameTag(loginData.getXname());
        this.controlledEntity.setNameTagAlwaysShow(true);
        this.controlledEntity.setLocationBeforeSpawn(new Location3d(currentPos.x(), currentPos.y(), currentPos.z(), dimension));

        var baseComponent = (EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) this.controlledEntity).getBaseComponent();
        baseComponent.setController(this);
        baseComponent.setUniqueId(this.loginData.getUuid());
        baseComponent.setPermissionCalculator(new OpPermissionCalculator(this));

        var event = new PlayerSpawnEvent(this, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        var loc = this.controlledEntity.getLocation();
        dimension = (AllayDimension) loc.dimension();
        var currentWorldName = dimension.getWorld().getWorldData().getDisplayName();
        var currentDimension = dimension.getDimensionType().getIdentifier().toString();
        var currentRotation = readVector2f(playerData.getNbt(), "Rotation");

        var positionChanged = loc.x() != currentPos.x() || loc.y() != currentPos.y() || loc.z() != currentPos.z();
        var rotationChanged = (float) loc.yaw() != currentRotation.x() || (float) loc.pitch() != currentRotation.y();
        var worldChanged = !Objects.equals(playerData.getWorld(), currentWorldName);
        var dimensionChanged = !Objects.equals(playerData.getDimension(), currentDimension);
        if (positionChanged || rotationChanged || worldChanged || dimensionChanged) {
            if (positionChanged || rotationChanged) {
                var builder = playerData.getNbt().toBuilder();
                writeVector3f(builder, "Pos", new org.joml.Vector3f(loc));
                writeVector2f(builder, "Rotation", (float) loc.yaw(), (float) loc.pitch());
                playerData.setNbt(builder.build());
            }

            playerData.setWorld(currentWorldName);
            playerData.setDimension(currentDimension);

            playerManager.getPlayerStorage().savePlayerData(this.getLoginData().getUuid(), playerData);
        }

        setClientState(ClientState.SPAWNED);

        // Dimension data and voxel shapes should be sent before start game
        var encoder = getProtocol().getEncoder();
        sendPacket(encoder.encodeDimensionData());
        sendPackets(encoder.encodeVoxelShapes());

        // Send StartGamePacket to the client first before we start sending chunks, otherwise
        // the chunks will be ignored by the client, and the client will be unable to join the server
        startGame(dimension.getWorld(), playerData, dimension);

        dimension.addPlayer(this);
        playerManager.addPlayer(this);

        sendPacket(encoder.encodeItemRegistry());
        sendPacket(encoder.encodeCreativeContent());
        sendPacket(encoder.encodeAvailableEntityIdentifiers());
        sendPackets(encoder.encodeSyncEntityProperties());
        sendPacket(encoder.encodeBiomeDefinitions());
        sendPacket(encoder.encodeCraftingData());
        sendPacket(encoder.encodeTrimData());
    }

    /**
     * Sends {@link StartGamePacket} to the client.
     */
    protected void startGame(World spawnWorld, PlayerData playerData, Dimension dimension) {
        sendPacket(getProtocol().getEncoder().encodeStartGame(
                spawnWorld,
                playerData,
                dimension,
                this.controlledEntity
        ));
    }

    public void completeLogin() {
        var playerManager = (AllayPlayerManager) Server.getInstance().getPlayerManager();
        if (playerManager.getPlayerCount() >= playerManager.getMaxPlayerCount()) {
            disconnect(TrKeys.MC_DISCONNECTIONSCREEN_SERVERFULL_TITLE);
            return;
        }

        var event = new PlayerLoginEvent(this, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        if (!event.call()) {
            disconnect(event.getDisconnectReason());
            return;
        }

        setClientState(ClientState.LOGGED_IN);
        sendPlayStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        sendPacket(getProtocol().getEncoder().encodeResourcePacksInfo());
    }

    protected void sendPlayStatus(PlayStatusPacket.Status status) {
        var packet = new PlayStatusPacket();
        packet.setStatus(status);
        sendPacket(packet);
    }

    protected void sendCommands() {
        var commands = Registries.COMMANDS.getContent().values().stream()
                .filter(command -> !command.isServerSideOnly() && this.controlledEntity.hasPermissions(command.getPermissions()))
                .map(this::encodeCommand)
                .toList();
        sendPacket(getProtocol().getEncoder().encodeCommands(commands));
    }

    protected void sendHudElements() {
        sendPackets(getProtocol().getEncoder().encodeHudElements(hiddenHudElements));
    }

    @Override
    public void viewDialog(Dialog dialog, Entity entity) {
        this.dialog = new Pair<>(dialog, entity);
        sendPackets(getProtocol().getEncoder().encodeDialog(dialog, entity));
    }

    @Override
    public Pair<Dialog, Entity> getDialog() {
        return this.dialog;
    }

    @Override
    public void removeDialog() {
        this.dialog = null;
    }

    @Override
    public void closeDialog() {
        if (this.dialog == null) {
            return;
        }

        sendPacket(getProtocol().getEncoder().encodeDialogClose(this.dialog.right()));

        this.dialog = null;
    }

    private PacketSignal handleBootstrapPacket(BedrockPacket packet) {
        if (!getClientState().canHandlePackets()) {
            return PacketSignal.HANDLED;
        }

        var event = new PacketReceiveEvent(this, packet);
        if (!event.call()) {
            return PacketSignal.HANDLED;
        }

        packet = event.getPacket();
        if (packet == null) {
            log.warn("Ignored a null packet produced by PacketReceiveEvent before protocol negotiation");
            return PacketSignal.HANDLED;
        }
        var processor = bootstrapProcessorHolder.getProcessor(packet);
        if (processor == null) {
            log.debug("Received a packet before protocol negotiation without a bootstrap processor: {}", packet);
            return PacketSignal.HANDLED;
        }

        var receiveTime = Server.getInstance().getTick();
        if (processor.handleAsync(this, packet, receiveTime) != PacketSignal.HANDLED) {
            processor.handleSync(this, packet, receiveTime);
        }
        return PacketSignal.HANDLED;
    }

    private class AllayPacketHandler implements BedrockPacketHandler {
        @Override
        public PacketSignal handlePacket(BedrockPacket packet) {
            var selectedSession = protocolSession;
            return selectedSession == null
                    ? handleBootstrapPacket(packet)
                    : selectedSession.receive(AllayPlayer.this, packet);
        }

        @Override
        public void onDisconnect(String reason) {
            synchronized (protocolLifecycleLock) {
                if (!activeProcessorHolder().setClientState(ClientState.DISCONNECTED, false)) {
                    // Failed to set the client state to DISCONNECTED from the current state. This usually
                    // happens when the client has already been disconnected by calling disconnect(). This
                    // is expected and should not be treated as an error
                    return;
                }
            }
            AllayPlayer.this.onDisconnect(reason);
        }
    }
}
