package org.allaymc.server.player;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.action.ContinueBreakAction;
import org.allaymc.api.block.action.SimpleBlockAction;
import org.allaymc.api.block.action.StartBreakAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.bossbar.BossBar;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.dialog.Dialog;
import org.allaymc.api.dialog.ModelSettings;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.*;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.server.PlayerDisconnectEvent;
import org.allaymc.api.eventbus.event.server.PlayerLoginEvent;
import org.allaymc.api.eventbus.event.server.PlayerSpawnEvent;
import org.allaymc.api.form.type.CustomForm;
import org.allaymc.api.form.type.Form;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.OpPermissionCalculator;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.permission.Tristate;
import org.allaymc.api.player.*;
import org.allaymc.api.player.HudElement;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.scoreboard.Scoreboard;
import org.allaymc.api.scoreboard.ScoreboardLine;
import org.allaymc.api.scoreboard.data.DisplaySlot;
import org.allaymc.api.scoreboard.data.SortOrder;
import org.allaymc.api.scoreboard.scorer.EntityScorer;
import org.allaymc.api.scoreboard.scorer.FakeScorer;
import org.allaymc.api.scoreboard.scorer.PlayerScorer;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.World;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.*;
import org.allaymc.api.world.sound.*;
import org.allaymc.server.AllayServer;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.command.tree.node.BaseNode;
import org.allaymc.server.container.ContainerNetworkInfo;
import org.allaymc.server.container.impl.AbstractPlayerContainer;
import org.allaymc.server.container.impl.FakeContainerImpl;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.container.processor.ContainerActionProcessor;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.network.multiversion.MultiVersion;
import org.allaymc.server.network.multiversion.MultiVersionHelper;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.utils.JSONUtils;
import org.allaymc.server.world.AllayDimension;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.netty.channel.raknet.RakServerChannel;
import org.cloudburstmc.netty.handler.codec.raknet.common.RakSessionCodec;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.FullContainerName;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.PacketSignal;
import org.cloudburstmc.protocol.common.SimpleDefinitionRegistry;
import org.cloudburstmc.protocol.common.util.OptionalBoolean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.awt.*;
import java.net.SocketAddress;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.allaymc.api.utils.AllayNBTUtils.readVector3f;
import static org.allaymc.api.utils.AllayNBTUtils.writeVector3f;
import static org.allaymc.server.network.NetworkHelper.toNetwork;
import static org.allaymc.server.network.NetworkHelper.toNetworkRemovalNotice;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayPlayer implements Player {

    // Constants used in UpdateSubChunkBlocksPacket
    protected static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    protected static final int BLOCK_UPDATE_NETWORK = 0b0010;
    protected static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    protected static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    // Constants used in BlockEventPacket
    protected static final int BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE = 1;
    protected static final int BLOCK_EVENT_DATA_OPEN_CHEST = 1;
    protected static final int BLOCK_EVENT_DATA_CLOSE_CHEST = 0;

    /**
     * A map which contains the network offset of some entities. The network offset is the additional offset in
     * y coordinate when sent over network. This is mostly the case for older entities such as player and TNT.
     */
    protected static final Supplier<Map<EntityType<?>, Float>> NETWORK_OFFSETS = Suppliers.memoize(() -> {
        var map = new HashMap<EntityType<?>, Float>();
        map.put(EntityTypes.PLAYER, 1.62f);
        map.put(EntityTypes.FALLING_BLOCK, 0.49f);
        map.put(EntityTypes.ITEM, 0.125f);
        map.put(EntityTypes.TNT, 0.49f);
        map.put(EntityTypes.FIREWORKS_ROCKET, 0.49f);
        return map;
    });

    protected final PacketProcessorHolder packetProcessorHolder;
    protected final AtomicInteger fullyJoinChunkThreshold;
    @Getter
    protected final BedrockServerSession session;

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
    @Getter
    protected Speed speed, flySpeed, verticalFlySpeed;

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

    // Dialog
    protected Pair<Dialog, Entity> dialog;

    // Hud
    protected Set<HudElement> hiddenHudElements;
    protected boolean shouldSendHudElements;

    public AllayPlayer(BedrockServerSession session) {
        this.session = session;
        this.session.setPacketHandler(new AllayPacketHandler());
        this.packetProcessorHolder = new PacketProcessorHolder();
        this.packetProcessorHolder.setClientState(ClientState.CONNECTED);
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
    }

    public static LevelChunkPacket createSubChunkLevelChunkPacket(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionInfo.dimensionId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setCachingEnabled(false);
        packet.setRequestSubChunks(true);
        // NOTICE: Sub chunk limit is bigger than zero
        packet.setSubChunkLimit(findHighestNonAirSectionY(chunk) - dimensionInfo.minSectionY());
        packet.setData(ChunkEncoder.writeToNetworkBiomeOnly(chunk));
        return packet;
    }

    private static int findHighestNonAirSectionY(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        for (int highest = dimensionInfo.maxSectionY(); highest > dimensionInfo.minSectionY(); highest--) {
            if (!chunk.getSection(highest).isAirSection()) {
                return highest;
            }
        }

        return dimensionInfo.minSectionY();
    }

    public static LevelChunkPacket createFullLevelChunkPacketChunk(AllayUnsafeChunk chunk) {
        var dimensionInfo = chunk.getDimensionInfo();
        var packet = new LevelChunkPacket();
        packet.setDimension(dimensionInfo.dimensionId());
        packet.setChunkX(chunk.getX());
        packet.setChunkZ(chunk.getZ());
        packet.setCachingEnabled(false);
        packet.setRequestSubChunks(false);
        packet.setSubChunksLength(dimensionInfo.chunkSectionCount());
        packet.setData(ChunkEncoder.writeToNetwork(chunk));
        return packet;
    }

    protected static int toNetworkBreakTime(double breakTime) {
        return breakTime == 0 ? 65535 : (int) (65535 / (breakTime * 20));
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
    }

    public void handlePacketSync(BedrockPacket packet, long receiveTime) {
        var processor = packetProcessorHolder.getProcessor(packet);
        if (processor == null) {
            log.warn("Received a sync packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientState());
            return;
        }
        processor.handleSync(this, packet, receiveTime);
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
        viewContents(this.controlledEntity.getContainer(ContainerTypes.INVENTORY));
        viewContents(this.controlledEntity.getContainer(ContainerTypes.OFFHAND));
        viewContents(this.controlledEntity.getContainer(ContainerTypes.ARMOR));
        viewPlayerPermission(this);
        viewPlayerListChange(playerManager.getPlayers().values(), true);

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
        var l = entity.getLocation();
        var position = Vector3f.from(l.x(), l.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f), l.z());
        var motion = switch (entity) {
            case EntityPhysicsComponent physicsComponent -> {
                var m = physicsComponent.getMotion();
                yield Vector3f.from(m.x(), m.y(), m.z());
            }
            default -> Vector3f.ZERO;
        };
        BedrockPacket packet = switch (entity) {
            case EntityPlayer player -> {
                var p = new AddPlayerPacket();
                p.setRuntimeEntityId(player.getRuntimeId());
                p.setUniqueEntityId(player.getUniqueId().getLeastSignificantBits());
                p.setUuid(player.getUniqueId());
                p.setUsername(player.getNameTag());
                p.setPlatformChatId("");
                p.setDeviceId("");
                // NOTICE: Player network offset is not used in AddPlayerPacket
                p.setPosition(Vector3f.from(l.x(), l.y(), l.z()));
                p.setMotion(motion);
                p.setRotation(Vector3f.from(l.pitch(), l.yaw(), l.yaw()));
                p.setGameType(toNetwork(player.getGameMode()));
                p.getMetadata().putAll(parseMetadata(entity));
                p.setHand(toNetwork(player.getContainer(ContainerTypes.INVENTORY).getItemInHand()));
                yield p;
            }
            case EntityItem item -> {
                var p = new AddItemEntityPacket();
                p.setRuntimeEntityId(item.getRuntimeId());
                p.setUniqueEntityId(item.getUniqueId().getLeastSignificantBits());
                p.setItemInHand(toNetwork(item.getItemStack()));
                p.setPosition(position);
                p.setMotion(motion);
                p.getMetadata().putAll(parseMetadata(entity));
                yield p;
            }
            case EntityPainting painting -> {
                var p = new AddPaintingPacket();
                p.setRuntimeEntityId(painting.getRuntimeId());
                p.setUniqueEntityId(painting.getUniqueId().getLeastSignificantBits());
                p.setMotive(painting.getPaintingType().getTitle());
                // NOTICE: The position being sent in AddPaintingPacket is the center of the painting's
                // aabb. It is not the position of the painting.
                var aabb = painting.getOffsetAABB();
                p.setPosition(Vector3f.from(
                        (aabb.minX() + aabb.maxX()) / 2,
                        (aabb.minY() + aabb.maxY()) / 2,
                        (aabb.minZ() + aabb.maxZ()) / 2
                ));
                p.setDirection(painting.getHorizontalFace().getHorizontalIndex());
                yield p;
            }
            default -> {
                var p = new AddEntityPacket();
                p.setRuntimeEntityId(entity.getRuntimeId());
                p.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
                p.setIdentifier(entity.getEntityType().getIdentifier().toString());
                p.setPosition(position);
                p.setMotion(motion);
                p.setRotation(Vector2f.from(l.pitch(), l.yaw()));
                p.setHeadRotation((float) l.yaw());
                p.setBodyRotation((float) l.yaw());
                p.getMetadata().putAll(parseMetadata(entity));
                yield p;
            }
        };
        sendPacket(packet);
    }

    @Override
    public void removeEntity(Entity entity) {
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getUniqueId().getLeastSignificantBits());
        sendPacket(packet);
    }

    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
        var gameMode = player.getGameMode();
        if (this.controlledEntity == player) {
            var packet1 = new SetPlayerGameTypePacket();
            packet1.setGamemode(toNetwork(player.getGameMode()).ordinal());
            sendPacket(packet1);

            var packet2 = new UpdateAdventureSettingsPacket();
            packet2.setNoPvM(gameMode == GameMode.SPECTATOR);
            packet2.setNoMvP(gameMode == GameMode.SPECTATOR);
            packet2.setShowNameTags(gameMode != GameMode.SPECTATOR);
            packet2.setImmutableWorld(gameMode == GameMode.SPECTATOR);
            packet2.setAutoJump(true);
            sendPacket(packet2);
        } else {
            var packet = new UpdatePlayerGameTypePacket();
            packet.setGameType(toNetwork(player.getGameMode()));
            packet.setEntityId(player.getRuntimeId());
            sendPacket(packet);
        }
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        BedrockPacket packet;
        if (AllayServer.getSettings().entitySettings().physicsEngineSettings().useDeltaMovePacket()) {
            packet = createDeltaMovePacket(entity, locationLastSent, newLocation, teleporting);
        } else {
            packet = createAbsoluteMovePacket(entity, locationLastSent, newLocation, teleporting);
        }
        sendPacket(packet);
    }

    protected BedrockPacket createDeltaMovePacket(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        var packet = new MoveEntityDeltaPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        var moveFlags = computeMoveFlags(entity, locationLastSent, newLocation);
        packet.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            packet.setX((float) newLocation.x());
            locationLastSent.x = newLocation.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            packet.setY((float) newLocation.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f));
            locationLastSent.y = newLocation.y();
        }
        if (moveFlags.contains(HAS_Z)) {
            packet.setZ((float) newLocation.z());
            locationLastSent.z = newLocation.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            packet.setPitch((float) newLocation.pitch());
            locationLastSent.pitch = newLocation.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            packet.setYaw((float) newLocation.yaw());
            packet.setHeadYaw((float) newLocation.yaw());
            locationLastSent.yaw = newLocation.yaw();
        }
        if (teleporting) {
            packet.getFlags().add(TELEPORTING);
        }
        if (entity instanceof EntityPhysicsComponent physicsComponent && physicsComponent.isOnGround()) {
            packet.getFlags().add(ON_GROUND);
        }
        return packet;
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Entity entity, Location3d locationLastSent, Location3dc newLocation) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = AllayServer.getSettings().entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (Math.abs(locationLastSent.x() - newLocation.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (Math.abs(locationLastSent.y() - newLocation.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (Math.abs(locationLastSent.z() - newLocation.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (Math.abs(locationLastSent.yaw() - newLocation.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (Math.abs(locationLastSent.pitch() - newLocation.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        return flags;
    }

    protected BedrockPacket createAbsoluteMovePacket(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        locationLastSent.set(newLocation);
        locationLastSent.setPitch(newLocation.pitch());
        locationLastSent.setYaw(newLocation.yaw());

        var packet = new MoveEntityAbsolutePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(newLocation.x(), newLocation.y() + NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f), newLocation.z()));
        packet.setRotation(Vector3f.from(newLocation.pitch(), newLocation.yaw(), newLocation.yaw()));
        packet.setTeleported(teleporting);
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            packet.setOnGround(physicsComponent.isOnGround());
        }

        return packet;
    }

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        sendPacket(packet);
    }

    @Override
    public void viewEntityState(Entity entity) {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(parseMetadata(entity));
        sendPacket(packet);
    }

    protected EntityDataMap parseMetadata(Entity entity) {
        var map = new EntityDataMap();
        addGenericMetadata(entity, map);
        addComponentSpecificMetadata(entity, map);
        addTypeSpecificMetadata(entity, map);
        return map;
    }

    /**
     * Adds generic metadata for the specified {@code entity} to the provided {@code map}.
     */
    @MultiVersion(version = "1.21.101", details = "1.21.101 client crashes if HAS_NPC is set to true for EntityItem. Fixed in newer client versions")
    protected void addGenericMetadata(Entity entity, EntityDataMap map) {
        map.setFlag(EntityFlag.HAS_COLLISION, entity.hasEntityCollision());
        map.setFlag(EntityFlag.CAN_CLIMB, true);
        map.setFlag(EntityFlag.INVISIBLE, entity.isInvisible());
        map.setFlag(EntityFlag.NO_AI, entity.isImmobile());
        var aabb = entity.getAABB();
        var nbt = NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", (float) (aabb.maxX() - aabb.minX()))
                .putFloat("MaxY", (float) (aabb.maxY() - aabb.minY()))
                .putFloat("MaxZ", (float) (aabb.maxZ() - aabb.minZ()))
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
        map.put(EntityDataTypes.HITBOX, nbt);
        map.put(EntityDataTypes.COLLISION_BOX, Vector3f.from(
                (float) (aabb.maxX() - aabb.minX()),
                (float) (aabb.maxY() - aabb.minY()),
                (float) (aabb.maxZ() - aabb.minZ())
        ));
        map.put(EntityDataTypes.SCALE, entity.getScale());
        // Minecraft 1.21.101 client crashes if HAS_NPC is set to true for EntityItem.
        // Other entity types are not affected. The issue is fixed in newer client versions.
        map.put(EntityDataTypes.HAS_NPC, !(entity instanceof EntityItem));
        if (entity.hasNameTag()) {
            map.setFlag(EntityFlag.CAN_SHOW_NAME, true);
            map.put(EntityDataTypes.NAME, entity.getNameTag());
            if (entity.isNameTagAlwaysShow()) {
                map.setFlag(EntityFlag.ALWAYS_SHOW_NAME, true);
                map.put(EntityDataTypes.NAMETAG_ALWAYS_SHOW, (byte) 1);
            }
        }
    }

    /**
     * Adds component-specific metadata to the {@code map} for the given {@code entity}. This method
     * applies metadata based on its specific components.
     */
    protected void addComponentSpecificMetadata(Entity entity, EntityDataMap map) {
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            map.setFlag(EntityFlag.HAS_GRAVITY, physicsComponent.hasGravity());
        }
        if (entity instanceof EntityLivingComponent livingComponent) {
            map.setFlag(EntityFlag.ON_FIRE, livingComponent.isOnFire());
            map.setFlag(EntityFlag.BREATHING, livingComponent.canBreathe());
            map.put(EntityDataTypes.AIR_SUPPLY, (short) livingComponent.getAirSupplyTicks());
            map.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) livingComponent.getAirSupplyMaxTicks());
            map.put(EntityDataTypes.VISIBLE_MOB_EFFECTS, encodeVisibleEffects(livingComponent.getEffects().values()));
        }
    }

    /**
     * Adds type-specific metadata for a given entity to the provided {@code EntityDataMap}. Based on
     * the type of the entity, this method updates the metadata to reflect the entity's specific state,
     * attributes, and flags.
     */
    protected void addTypeSpecificMetadata(Entity entity, EntityDataMap map) {
        switch (entity) {
            case EntityTnt tnt -> {
                map.setFlag(EntityFlag.IGNITED, true);
                map.put(EntityDataTypes.FUSE_TIME, tnt.getFuseTime());
            }
            case EntityPlayer player -> {
                map.setFlag(EntityFlag.SPRINTING, player.isSprinting());
                map.setFlag(EntityFlag.SNEAKING, player.isSneaking());
                map.setFlag(EntityFlag.SWIMMING, player.isSwimming());
                map.setFlag(EntityFlag.GLIDING, player.isGliding());
                map.setFlag(EntityFlag.CRAWLING, player.isCrawling());
                map.setFlag(EntityFlag.USING_ITEM, player.isUsingItemInAir());
                map.setFlag(EntityFlag.BREATHING, player.canBreathe());
                map.put(EntityDataTypes.AIR_SUPPLY, (short) player.getAirSupplyTicks());
                map.put(EntityDataTypes.AIR_SUPPLY_MAX, (short) player.getAirSupplyMaxTicks());
                if (player.hasScoreTag()) {
                    map.put(EntityDataTypes.SCORE, player.getScoreTag());
                }
            }
            case EntityFallingBlock fallingBlock -> {
                map.setFlag(EntityFlag.FIRE_IMMUNE, true);
                map.put(EntityDataTypes.VARIANT, fallingBlock.getBlockState().blockStateHash());
            }
            case EntityXpOrb xpOrb -> {
                map.put(EntityDataTypes.VALUE, xpOrb.getExperienceValue());
            }
            case EntityArrow arrow -> {
                map.setFlag(EntityFlag.CRITICAL, arrow.isCritical());
                var potionType = arrow.getPotionType();
                if (potionType != null) {
                    map.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) (potionType.ordinal() + 1));
                }
            }
            case EntityFireworksRocket firework -> {
                var nbt = NbtMap.builder()
                        .putCompound("Fireworks", NbtMap.builder()
                                .putList("Explosions", NbtType.COMPOUND, firework.getExplosions().stream().map(FireworkExplosion::saveNBT).toList())
                                .putByte("Flight", (byte) (firework.getExistenceTicks() / 20))
                                .build()
                        )
                        .build();
                map.put(EntityDataTypes.DISPLAY_FIREWORK, nbt);

                var attachedPlayer = firework.getAttachedPlayer();
                if (attachedPlayer != null) {
                    map.put(EntityDataTypes.CUSTOM_DISPLAY, (byte) attachedPlayer.getRuntimeId());
                }
            }
            case EntityEnderCrystal enderCrystal -> {
                map.setFlag(EntityFlag.SHOW_BOTTOM, enderCrystal.isBaseVisible());
            }
            default -> {
            }
        }
    }

    protected long encodeVisibleEffects(Collection<EffectInstance> effects) {
        long visibleEffects = 0;
        for (var effect : effects) {
            if (!effect.isVisible()) {
                continue;
            }

            visibleEffects = (visibleEffects << 7) | ((long) effect.getType().getId() << 1) | (effect.isAmbient() ? 1 : 0);
        }
        return visibleEffects;
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        var container = entity.getContainer(ContainerTypes.INVENTORY);
        var handSlot = container.getHandSlot();
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);
        packet.setItem(toNetwork(container.getItemInHand()));
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);
        sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        var container = entity.getContainer(ContainerTypes.OFFHAND);
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.OFFHAND);
        // Network slot index for offhand is 1, see FullContainerType.OFFHAND. Field `hotbarSlot` is unused
        packet.setInventorySlot(1);
        packet.setItem(toNetwork(container.getOffhand()));
        sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        var container = entity.getContainer(ContainerTypes.ARMOR);
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setBody(toNetwork(ItemAirStack.AIR_STACK));
        packet.setHelmet(toNetwork(container.getHelmet()));
        packet.setChestplate(toNetwork(container.getChestplate()));
        packet.setLeggings(toNetwork(container.getLeggings()));
        packet.setBoots(toNetwork(container.getBoots()));
        sendPacket(packet);
    }

    @Override
    public void viewEntityAnimation(Entity entity, EntityAnimation animation) {
        var packet = new AnimateEntityPacket();
        packet.setAnimation(animation.name());
        packet.setNextState(animation.nextState());
        packet.setStopExpression(animation.stopCondition());
        packet.setController(animation.controller());
        packet.getRuntimeEntityIds().add(entity.getRuntimeId());
        sendPacket(packet);
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        if (player.getSkin() == null) {
            return;
        }

        var skin = SkinConvertor.toSerializedSkin(player.getSkin());
        var packet = new PlayerSkinPacket();
        packet.setUuid(player.getUniqueId());
        packet.setSkin(skin);
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(AllayServer.getSettings().resourcePackSettings().trustAllSkins());
        sendPacket(packet);
    }

    @Override
    public void viewEntityAction(Entity entity, EntityAction action) {
        switch (action) {
            case SimpleEntityAction.SWING_ARM -> {
                if (entity instanceof EntityPlayer player) {
                    if (this.controlledEntity == player) {
                        // Do not send it to the player itself
                        return;
                    }

                    var packet = new AnimatePacket();
                    packet.setAction(AnimatePacket.Action.SWING_ARM);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    sendPacket(packet);

                } else {
                    var packet = new EntityEventPacket();
                    packet.setType(EntityEventType.ATTACK_START);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    sendPacket(packet);
                }
            }
            case SimpleEntityAction.HURT -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.HURT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                sendPacket(packet);
            }
            case SimpleEntityAction.DEATH -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.DEATH);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                sendPacket(packet);
            }
            case SimpleEntityAction.EAT -> {
                if (entity instanceof ContainerHolder holder && holder.hasContainer(ContainerTypes.INVENTORY)) {
                    var item = holder.getContainer(ContainerTypes.INVENTORY).getItemInHand();
                    var packet = new EntityEventPacket();
                    packet.setType(EntityEventType.EATING_ITEM);
                    packet.setRuntimeEntityId(entity.getRuntimeId());
                    packet.setData((item.getItemType().getRuntimeId() << 16) | item.getMeta());
                    sendPacket(packet);
                }
            }
            case SimpleEntityAction.FIREWORK_EXPLODE -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.FIREWORK_EXPLODE);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                sendPacket(packet);
            }
            case SimpleEntityAction.TOTEM_USE -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.CONSUME_TOTEM);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                sendPacket(packet);
            }
            case PickedUpAction(Entity picker) -> {
                var packet = new TakeItemEntityPacket();
                packet.setRuntimeEntityId(picker.getRuntimeId());
                packet.setItemRuntimeEntityId(entity.getRuntimeId());
                sendPacket(packet);
            }
            case ArrowShakeAction(int times) -> {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.ARROW_SHAKE);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                packet.setData(times);
                sendPacket(packet);
            }
            case CriticalHit(int count) -> {
                var packet = new AnimatePacket();
                packet.setAction(AnimatePacket.Action.CRITICAL_HIT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                packet.setData(count);
                sendPacket(packet);
            }
            case EnchantedHit(int count) -> {
                var packet = new AnimatePacket();
                packet.setAction(AnimatePacket.Action.MAGIC_CRITICAL_HIT);
                packet.setRuntimeEntityId(entity.getRuntimeId());
                packet.setData(count);
                sendPacket(packet);
            }
            default -> throw new IllegalStateException("Unhandled entity action type: " + action.getClass().getSimpleName());
        }
    }

    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId) {
        var packet = new EmotePacket();
        packet.setRuntimeEntityId(player.getRuntimeId());
        packet.setEmoteId(emoteId.toString());
        packet.getFlags().add(EmoteFlag.SERVER_SIDE);
        sendPacket(packet);
    }

    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
        var packet = new MobEffectPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        if (newEffect == null) {
            Preconditions.checkNotNull(oldEffect);
            // Effect is removed
            packet.setEffectId(oldEffect.getType().getId());
            packet.setEvent(MobEffectPacket.Event.REMOVE);
        } else {
            packet.setEffectId(newEffect.getType().getId());
            packet.setAmplifier(newEffect.getAmplifier());
            packet.setParticles(newEffect.isVisible());
            packet.setDuration(newEffect.getDuration());
            packet.setEvent(oldEffect == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        }
        sendPacket(packet);
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
        var packet = new NetworkChunkPublisherUpdatePacket();
        var location = this.controlledEntity.getLocation();
        packet.setPosition(Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(this.controlledEntity.getChunkLoadingRadius() << 4);
        return packet;
    }

    protected LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        var lcp = new LevelChunkPacket[1];
        chunk.applyOperation(unsafeChunk -> {
            if (AllayServer.getSettings().worldSettings().useSubChunkSendingSystem()) {
                lcp[0] = createSubChunkLevelChunkPacket((AllayUnsafeChunk) unsafeChunk);
            } else {
                lcp[0] = createFullLevelChunkPacketChunk((AllayUnsafeChunk) unsafeChunk);
            }
        }, OperationType.READ, OperationType.READ);
        return lcp[0];
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
        var packet = new SetTimePacket();
        packet.setTime(timeOfDay);
        sendPacket(packet);
    }

    @Override
    public void viewGameRules(GameRules gameRules) {
        var packet = new GameRulesChangedPacket();
        packet.getGameRules().addAll(NetworkHelper.toNetwork(gameRules.getGameRules()));
        sendPacket(packet);
    }

    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
        var packet = new UpdateBlockPacket();
        packet.setBlockPosition(toNetwork(pos));
        packet.setDataLayer(layer);
        packet.setDefinition(blockState::blockStateHash);
        packet.getFlags().add(UpdateBlockPacket.Flag.NETWORK);
        sendPacket(packet);
    }

    @Override
    public void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates) {
        var packets = new UpdateSubChunkBlocksPacket[chunk.getDimensionInfo().chunkSectionCount()];
        encodeBlockUpdates(packets, chunk, blockUpdates, false);
        encodeBlockUpdates(packets, chunk, extraBlockUpdates, true);
        for (var packet : packets) {
            if (packet == null) {
                // packet can be null if there is no update in a chunk section
                continue;
            }

            sendPacket(packet);
        }
    }

    @Override
    public void viewBlockAction(Vector3ic p, BlockAction action) {
        var pos = toNetwork(p);
        var pos3f = Vector3f.from(p.x(), p.y(), p.z());
        switch (action) {
            case SimpleBlockAction.OPEN -> {
                var packet = new BlockEventPacket();
                packet.setBlockPosition(pos);
                packet.setEventType(BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                packet.setEventData(BLOCK_EVENT_DATA_OPEN_CHEST);
                sendPacket(packet);
            }
            case SimpleBlockAction.CLOSE -> {
                var packet = new BlockEventPacket();
                packet.setBlockPosition(pos);
                packet.setEventType(BLOCK_EVENT_TYPE_CHANGE_CHEST_STATE);
                packet.setEventData(BLOCK_EVENT_DATA_CLOSE_CHEST);
                sendPacket(packet);
            }
            case StartBreakAction(double breakTime) -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_START_BREAK);
                packet.setPosition(pos3f);
                packet.setData(toNetworkBreakTime(breakTime));
                sendPacket(packet);
            }
            case ContinueBreakAction(double breakTime) -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_UPDATE_BREAK);
                packet.setPosition(pos3f);
                packet.setData(toNetworkBreakTime(breakTime));
                sendPacket(packet);
            }
            case SimpleBlockAction.STOP_BREAK -> {
                var packet = new LevelEventPacket();
                packet.setType(LevelEvent.BLOCK_STOP_BREAK);
                packet.setPosition(pos3f);
                sendPacket(packet);
            }
            default -> throw new IllegalStateException("Unhandled block action type: " + action.getClass().getSimpleName());
        }
    }

    protected void encodeBlockUpdates(UpdateSubChunkBlocksPacket[] packets, Chunk chunk, Collection<BlockUpdate> blockUpdates, boolean isExtraLayer) {
        if (blockUpdates.isEmpty()) {
            return;
        }

        for (var update : blockUpdates) {
            var sectionY = update.y() >> 4;
            var index = sectionY - chunk.getDimensionInfo().minSectionY();
            UpdateSubChunkBlocksPacket packet;

            if ((packet = packets[index]) == null) {
                packet = new UpdateSubChunkBlocksPacket();
                packet.setChunkX(chunk.getX() << 4);
                packet.setChunkY(sectionY << 4);
                packet.setChunkZ(chunk.getZ() << 4);
                packets[index] = packet;
            }

            // updateFlags is a combination of flags that specify the way the block is updated client-side. It is a
            // combination of the flags above, but typically sending only the BLOCK_UPDATE_NETWORK flag is sufficient.
            var entry = new BlockChangeEntry(
                    Vector3i.from(update.x(), update.y(), update.z()), update.blockState()::blockStateHash,
                    BLOCK_UPDATE_NETWORK, -1, BlockChangeEntry.MessageType.NONE
            );

            if (isExtraLayer) {
                packet.getExtraBlocks().add(entry);
            } else {
                packet.getStandardBlocks().add(entry);
            }
        }
    }

    @Override
    public void viewSound(Sound sound, Vector3dc p, boolean relative) {
        LevelSoundEventPacket packet = new LevelSoundEventPacket();
        var pos = toNetwork(MathUtils.toVec3f(p));
        packet.setPosition(pos);
        packet.setIdentifier(":");
        packet.setExtraData(-1);
        packet.setRelativeVolumeDisabled(!relative);

        switch (sound) {
            case SimpleSound.FIREWORK_LAUNCH -> packet.setSound(SoundEvent.LAUNCH);
            case SimpleSound.FIREWORK_HUGE_BLAST -> packet.setSound(SoundEvent.LARGE_BLAST);
            case SimpleSound.FIREWORK_BLAST -> packet.setSound(SoundEvent.BLAST);
            case SimpleSound.FIREWORK_FLICKER -> packet.setSound(SoundEvent.TWINKLE);
            case SimpleSound.FURNACE_CRACKLE -> packet.setSound(SoundEvent.FURNACE_USE);
            case SimpleSound.CAMPFIRE_CRACKLE -> packet.setSound(SoundEvent.CAMPFIRE_CRACKLE);
            case SimpleSound.BLAST_FURNACE_CRACKLE -> packet.setSound(SoundEvent.BLAST_FURNACE_USE);
            case SimpleSound.SMOKER_CRACKLE -> packet.setSound(SoundEvent.SMOKER_USE);
            case SimpleSound.POTION_BREWED -> packet.setSound(SoundEvent.POTION_BREWED);
            case SimpleSound.USE_SPYGLASS -> packet.setSound(SoundEvent.USE_SPYGLASS);
            case SimpleSound.STOP_USING_SPYGLASS -> packet.setSound(SoundEvent.STOP_USING_SPYGLASS);
            case SimpleSound.FIRE_EXTINGUISH -> packet.setSound(SoundEvent.EXTINGUISH_FIRE);
            case SimpleSound.IGNITE -> packet.setSound(SoundEvent.IGNITE);
            case SimpleSound.BURNING -> packet.setSound(SoundEvent.PLAYER_HURT_ON_FIRE);
            case SimpleSound.DROWNING -> packet.setSound(SoundEvent.PLAYER_HURT_DROWN);
            case SimpleSound.BURP -> packet.setSound(SoundEvent.BURP);
            case SimpleSound.DENY -> packet.setSound(SoundEvent.DENY);
            case SimpleSound.CHEST_CLOSE -> packet.setSound(SoundEvent.CHEST_CLOSED);
            case SimpleSound.CHEST_OPEN -> packet.setSound(SoundEvent.CHEST_OPEN);
            case SimpleSound.ENDER_CHEST_CLOSE -> packet.setSound(SoundEvent.ENDERCHEST_CLOSED);
            case SimpleSound.ENDER_CHEST_OPEN -> packet.setSound(SoundEvent.ENDERCHEST_OPEN);
            case SimpleSound.SHULKER_BOX_CLOSE -> packet.setSound(SoundEvent.SHULKERBOX_CLOSED);
            case SimpleSound.SHULKER_BOX_OPEN -> packet.setSound(SoundEvent.SHULKERBOX_OPEN);
            case SimpleSound.BARREL_CLOSE -> packet.setSound(SoundEvent.BARREL_CLOSE);
            case SimpleSound.BARREL_OPEN -> packet.setSound(SoundEvent.BARREL_OPEN);
            case SimpleSound.FIZZ -> packet.setSound(SoundEvent.FIZZ);
            case SimpleSound.SPONGE_ABSORB -> packet.setSound(SoundEvent.SPONGE_ABSORB);
            case SimpleSound.GLASS_BREAK -> packet.setSound(SoundEvent.GLASS);
            case SimpleSound.BOW_SHOOT -> packet.setSound(SoundEvent.BOW);
            case SimpleSound.CROSSBOW_SHOOT -> packet.setSound(SoundEvent.CROSSBOW_SHOOT);
            case SimpleSound.ARROW_HIT -> packet.setSound(SoundEvent.BOW_HIT);
            case SimpleSound.MUSIC_DISC_END -> packet.setSound(SoundEvent.STOP_RECORD);
            case SimpleSound.COMPOSTER_EMPTY -> packet.setSound(SoundEvent.COMPOSTER_EMPTY);
            case SimpleSound.COMPOSTER_FILL -> packet.setSound(SoundEvent.COMPOSTER_FILL);
            case SimpleSound.COMPOSTER_FILL_LAYER -> packet.setSound(SoundEvent.COMPOSTER_FILL_LAYER);
            case SimpleSound.COMPOSTER_READY -> packet.setSound(SoundEvent.COMPOSTER_READY);
            case SimpleSound.LECTERN_BOOK_PLACE -> packet.setSound(SoundEvent.LECTERN_BOOK_PLACE);
            case SimpleSound.WAXED_SIGN_FAILED_INTERACTION -> packet.setSound(SoundEvent.WAXED_SIGN_INTERACT_FAIL);
            case SimpleSound.TELEPORT -> packet.setSound(SoundEvent.TELEPORT);
            case SimpleSound.DECORATED_POT_INSERT_FAILED -> packet.setSound(SoundEvent.DECORATED_POT_INSERT_FAILED);
            case SimpleSound.ITEM_BREAK -> packet.setSound(SoundEvent.BREAK);
            case SimpleSound.CHORUS_FLOWER_GROW -> packet.setSound(SoundEvent.CHORUS_GROW);
            case SimpleSound.END_PORTAL_FRAME_FILLED -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_FRAME_FILL);
            case SimpleSound.END_PORTAL_SPAWN -> packet.setSound(SoundEvent.BLOCK_END_PORTAL_SPAWN);
            case SimpleSound.PAINTING_PLACE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_PLACE);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GLOW_INK_SAC_USED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INK_SACE_USED);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.DOOR_CRASH -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ZOMBIE_DOOR_CRASH);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.EXPLOSION -> {
                packet.setSound(SoundEvent.EXPLODE);
            }
            case SimpleSound.THUNDER -> {
                packet.setSound(SoundEvent.THUNDER);
                packet.setIdentifier("minecraft:lightning_bolt");
            }
            case SimpleSound.CLICK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_CLICK);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.WAXED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_ON);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.WAX_REMOVED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_OFF);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.COPPER_SCRAPED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_SCRAPE);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.POP -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INFINITY_ARROW_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_ADD -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ADD);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_REMOVE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_REMOVE);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_ROTATE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ROTATE);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_WARNING -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_WARNING);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_SHOOT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TNT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_FUSE);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_LAND -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_LAND);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_USE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_USED);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_BREAK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_BROKEN);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.FIRE_CHARGE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_BLAZE_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TOTEM -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_TOTEM_USED);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_THROW -> {
                packet.setSound(SoundEvent.THROW);
                packet.setIdentifier("minecraft:player");
            }
            case SimpleSound.LEVEL_UP -> {
                packet.setSound(SoundEvent.LEVELUP);
                packet.setExtraData(0x10000000);
            }
            case SimpleSound.EXPERIENCE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_EXPERIENCE_ORB_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                sendPacket(levelEvent);
                return;
            }
            case EquipItemSound so -> packet.setSound(getEquipSound(so.itemType()));
            case NoteSound so -> {
                packet.setSound(SoundEvent.NOTE);
                packet.setExtraData((so.instrument().ordinal() << 8) | so.pitch());
            }
            case FallSound so -> {
                packet.setIdentifier("minecraft:player");
                if (so.distance() > 4) {
                    packet.setSound(SoundEvent.FALL_BIG);
                } else {
                    packet.setSound(SoundEvent.FALL_SMALL);
                }
            }
            case DoorOpenSound so -> {
                packet.setSound(SoundEvent.DOOR_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case DoorCloseSound so -> {
                packet.setSound(SoundEvent.DOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonPressSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ButtonReleaseSound so -> {
                packet.setSound(SoundEvent.BUTTON_CLICK_OFF);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case TrapdoorOpenSound so -> {
                packet.setSound(SoundEvent.TRAPDOOR_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case TrapdoorCloseSound so -> {
                packet.setSound(SoundEvent.TRAPDOOR_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case FenceGateOpenSound so -> {
                packet.setSound(SoundEvent.FENCE_GATE_OPEN);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case FenceGateCloseSound so -> {
                packet.setSound(SoundEvent.FENCE_GATE_CLOSE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockPlaceSound so -> {
                packet.setSound(SoundEvent.PLACE);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case BlockBreakingSound so -> {
                packet.setSound(SoundEvent.HIT);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case ItemUseOnBlockSound so -> {
                packet.setSound(SoundEvent.ITEM_USE_ON);
                packet.setExtraData(so.blockState().blockStateHash());
            }
            case AttackSound so -> {
                packet.setIdentifier("minecraft:player");
                if (!so.damage()) {
                    packet.setSound(SoundEvent.ATTACK_NODAMAGE);
                } else {
                    packet.setSound(SoundEvent.ATTACK_STRONG);
                }
            }
            case BucketFillSound so -> {
                if (so.water()) {
                    packet.setSound(SoundEvent.BUCKET_FILL_WATER);
                } else {
                    packet.setSound(SoundEvent.BUCKET_FILL_LAVA);
                }
            }
            case BucketEmptySound so -> {
                if (so.water()) {
                    packet.setSound(SoundEvent.BUCKET_EMPTY_WATER);
                } else {
                    packet.setSound(SoundEvent.BUCKET_EMPTY_LAVA);
                }
            }
            case CrossbowLoadSound so -> {
                switch (so.stage()) {
                    case START -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_START : SoundEvent.CROSSBOW_LOADING_START);
                    case MIDDLE -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_MIDDLE : SoundEvent.CROSSBOW_LOADING_MIDDLE);
                    case END -> packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_END : SoundEvent.CROSSBOW_LOADING_END);
                }
            }
            case MusicDiscPlaySound so -> {
                switch (so.discType()) {
                    case DISC_13 -> packet.setSound(SoundEvent.RECORD_13);
                    case DISC_CAT -> packet.setSound(SoundEvent.RECORD_CAT);
                    case DISC_BLOCKS -> packet.setSound(SoundEvent.RECORD_BLOCKS);
                    case DISC_CHIRP -> packet.setSound(SoundEvent.RECORD_CHIRP);
                    case DISC_FAR -> packet.setSound(SoundEvent.RECORD_FAR);
                    case DISC_MALL -> packet.setSound(SoundEvent.RECORD_MALL);
                    case DISC_MELLOHI -> packet.setSound(SoundEvent.RECORD_MELLOHI);
                    case DISC_STAL -> packet.setSound(SoundEvent.RECORD_STAL);
                    case DISC_STRAD -> packet.setSound(SoundEvent.RECORD_STRAD);
                    case DISC_WARD -> packet.setSound(SoundEvent.RECORD_WARD);
                    case DISC_11 -> packet.setSound(SoundEvent.RECORD_11);
                    case DISC_WAIT -> packet.setSound(SoundEvent.RECORD_WAIT);
                    case DISC_OTHERSIDE -> packet.setSound(SoundEvent.RECORD_OTHERSIDE);
                    case DISC_PIGSTEP -> packet.setSound(SoundEvent.RECORD_PIGSTEP);
                    case DISC_5 -> packet.setSound(SoundEvent.RECORD_5);
                    case DISC_RELIC -> packet.setSound(SoundEvent.RECORD_RELIC);
                    case DISC_CREATOR -> packet.setSound(SoundEvent.RECORD_CREATOR);
                    case DISC_CREATOR_MUSIC_BOX -> packet.setSound(SoundEvent.RECORD_CREATOR_MUSIC_BOX);
                    case DISC_PRECIPICE -> packet.setSound(SoundEvent.RECORD_PRECIPICE);
                    case DISC_TEARS -> packet.setSound(SoundEvent.RECORD_TEARS);
                    case DISC_LAVA_CHICKEN -> packet.setSound(SoundEvent.RECORD_LAVA_CHICKEN);
                    default -> throw new IllegalArgumentException();
                }
            }
            case DecoratedPotInsertedSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(SoundNames.BLOCK_DECORATED_POT_INSERT);
                playSound.setPosition(pos);
                playSound.setVolume(1.0f);
                playSound.setPitch(0.7f + 0.5f * (float) so.progress());
                sendPacket(playSound);
                return;
            }
            case CustomSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(so.soundName());
                playSound.setPosition(pos);
                playSound.setVolume(so.volume());
                playSound.setPitch(so.pitch());
                sendPacket(playSound);
                return;
            }
            default -> throw new IllegalArgumentException("Unhandled sound type: " + sound.getClass().getSimpleName());
        }

        sendPacket(packet);
    }

    @Override
    public void stopSound(String soundName) {
        var packet = new StopSoundPacket();
        packet.setSoundName(Objects.requireNonNullElse(soundName, ""));
        packet.setStoppingAllSound(soundName == null);
        sendPacket(packet);
    }

    protected SoundEvent getEquipSound(ItemType<?> itemType) {
        if (itemType == ItemTypes.ELYTRA) {
            return SoundEvent.ARMOR_EQUIP_ELYTRA;
        }

        return switch (ItemHelper.getArmorTier(itemType)) {
            case LEATHER -> SoundEvent.ARMOR_EQUIP_LEATHER;
            case IRON -> SoundEvent.ARMOR_EQUIP_IRON;
            case CHAIN -> SoundEvent.ARMOR_EQUIP_CHAIN;
            case GOLD -> SoundEvent.ARMOR_EQUIP_GOLD;
            case DIAMOND, NETHERITE -> SoundEvent.ARMOR_EQUIP_DIAMOND;
            case null -> SoundEvent.ARMOR_EQUIP_GENERIC;
        };
    }

    @Override
    public void viewParticle(Particle particle, Vector3dc p) {
        var pos = toNetwork(MathUtils.toVec3f(p));
        var packet = new LevelEventPacket();
        packet.setPosition(pos);
        switch (particle) {
            case SimpleParticle.EXPLODE -> packet.setType(ParticleType.EXPLODE);
            case SimpleParticle.HUGE_EXPLOSION -> packet.setType(LevelEvent.PARTICLE_EXPLOSION);
            case SimpleParticle.BONE_MEAL -> packet.setType(LevelEvent.PARTICLE_CROP_GROWTH);
            case SimpleParticle.BLOCK_FORCE_FIELD -> packet.setType(LevelEvent.PARTICLE_DENY_BLOCK);
            case SimpleParticle.ENDERMAN_TELEPORT -> packet.setType(LevelEvent.PARTICLE_TELEPORT);
            case SimpleParticle.EVAPORATE -> packet.setType(LevelEvent.PARTICLE_EVAPORATE_WATER);
            case SimpleParticle.SNOWBALL_POOF -> packet.setType(ParticleType.SNOWBALL_POOF);
            case SimpleParticle.ENTITY_FLAME -> packet.setType(ParticleType.MOB_FLAME);
            case SimpleParticle.WATER_DRIP -> packet.setType(ParticleType.DRIP_WATER);
            case SimpleParticle.LAVA_DRIP -> packet.setType(ParticleType.DRIP_LAVA);
            case SimpleParticle.LAVA -> packet.setType(ParticleType.LAVA);
            case SimpleParticle.DUST_PLUME -> packet.setType(ParticleType.DUST_PLUME);
            case SimpleParticle.WHITE_SMOKE -> packet.setType(ParticleType.WHITE_SMOKE);
            case SimpleParticle.FIREWORK_CONTRAIL -> packet.setType(ParticleType.FIREWORKS);
            case CustomParticle pa -> {
                var pk = new SpawnParticleEffectPacket();
                pk.setDimensionId(this.controlledEntity.getDimension().getDimensionInfo().dimensionId());
                pk.setIdentifier(pa.particleName());
                pk.setMolangVariablesJson(Optional.ofNullable(pa.moLangVariables()));
                pk.setPosition(pos);
                sendPacket(pk);
                return;
            }
            case DragonEggTeleportParticle pa -> {
                int xSign = pa.diff().x() < 0 ? 1 << 24 : 0;
                int ySign = pa.diff().y() < 0 ? 1 << 25 : 0;
                int zSign = pa.diff().z() < 0 ? 1 << 26 : 0;
                int data = (Math.abs(pa.diff().x()) << 16) |
                           (Math.abs(pa.diff().y()) << 8) |
                           (Math.abs(pa.diff().z())) | xSign | ySign | zSign;

                packet.setType(LevelEvent.PARTICLE_DRAGON_EGG);
                packet.setData(data);
            }
            case NoteParticle pa -> {
                var pk = new BlockEventPacket();
                pk.setBlockPosition(Vector3i.from((int) p.x(), (int) p.y(), (int) p.z()));
                pk.setEventType(pa.instrument().ordinal());
                pk.setEventData(pa.pitch());
                sendPacket(pk);
                return;
            }
            case BlockBreakParticle pa -> {
                packet.setType(LevelEvent.PARTICLE_DESTROY_BLOCK);
                packet.setData(pa.blockState().blockStateHash());
            }
            case PunchBlockParticle pa -> {
                packet.setType(switch (Objects.requireNonNull(pa.blockFace())) {
                    case UP -> LevelEvent.PARTICLE_BREAK_BLOCK_UP;
                    case DOWN -> LevelEvent.PARTICLE_BREAK_BLOCK_DOWN;
                    case NORTH -> LevelEvent.PARTICLE_BREAK_BLOCK_NORTH;
                    case SOUTH -> LevelEvent.PARTICLE_BREAK_BLOCK_SOUTH;
                    case WEST -> LevelEvent.PARTICLE_BREAK_BLOCK_WEST;
                    case EAST -> LevelEvent.PARTICLE_BREAK_BLOCK_EAST;
                });
                packet.setData(pa.blockState().blockStateHash());
            }
            case FlameParticle pa -> {
                packet.setType(ParticleType.FLAME);
                packet.setData(pa.color().getRGB());
            }
            case ItemBreakParticle pa -> {
                packet.setType(ParticleType.ICON_CRACK);
                packet.setData((pa.itemStack().getItemType().getRuntimeId() << 16) | pa.itemStack().getMeta());
            }
            case SplashParticle pa -> {
                packet.setType(LevelEvent.PARTICLE_POTION_SPLASH);
                packet.setData(pa.color().getRGB());
            }
            case EffectParticle pa -> {
                packet.setType(ParticleType.MOB_SPELL);
                packet.setData(pa.color().getRGB());
            }
            case DustParticle pa -> {
                packet.setType(ParticleType.FALLING_DUST);
                packet.setData(pa.color().getRGB());
            }
            default -> throw new IllegalArgumentException("Unhandled particle type: " + particle.getClass().getSimpleName());
        }
        sendPacket(packet);
    }

    @Override
    public void viewWeather(Weather weather) {
        var packet1 = new LevelEventPacket();
        packet1.setPosition(Vector3f.ZERO);
        packet1.setType(LevelEvent.STOP_RAINING);
        if (weather != Weather.CLEAR) {
            packet1.setType(LevelEvent.START_RAINING);
            packet1.setData(65535);
        }
        sendPacket(packet1);

        var packet2 = new LevelEventPacket();
        packet2.setPosition(Vector3f.ZERO);
        packet2.setType(LevelEvent.STOP_THUNDERSTORM);
        if (weather == Weather.THUNDER) {
            packet2.setType(LevelEvent.START_THUNDERSTORM);
            packet2.setData(65535);
        }
        sendPacket(packet2);
    }

    @Override
    public void viewDebugShape(DebugShape debugShape) {
        var packet = new DebugDrawerPacket();
        packet.getShapes().add(toNetwork(debugShape, this.controlledEntity.getDimension().getDimensionInfo().dimensionId()));
        sendPacket(packet);
    }

    @Override
    public void viewDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new DebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(toNetwork(debugShape, this.controlledEntity.getDimension().getDimensionInfo().dimensionId()));
        }
        sendPacket(packet);
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
        var packet = new DebugDrawerPacket();
        packet.getShapes().add(toNetworkRemovalNotice(debugShape));
        sendPacket(packet);
    }

    @Override
    public void removeDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new DebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(toNetworkRemovalNotice(debugShape));
        }
        sendPacket(packet);
    }

    @Override
    public void viewBlockEntity(BlockEntity blockEntity) {
        if (!((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).sendToClient()) {
            return;
        }

        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(toNetwork(blockEntity.getPosition()));
        packet.setData(blockEntity.saveNBT());
        sendPacket(packet);
    }

    @Override
    public void viewContents(Container container) {
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
        var packet = new InventoryContentPacket();
        packet.setContainerId(containerId);
        // Client expects both zero if we do not use FullContainerName, and the id of
        // ContainerSlotType.ANVIL_INPUT is zero
        packet.setContainerNameData(new FullContainerName(ContainerSlotType.ANVIL_INPUT, null));
        packet.setContents(NetworkHelper.toNetwork(container.getItemStacks()));
        sendPacket(packet);
    }

    @Override
    public void viewSlot(Container container, int slot) {
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
        var packet = new InventorySlotPacket();
        packet.setContainerId(containerId);
        packet.setSlot(ContainerActionProcessor.toNetworkSlotIndex(container, slot));
        packet.setContainerNameData(new FullContainerName(ContainerActionProcessor.getSlotType(container, slot), null));
        packet.setItem(NetworkHelper.toNetwork(container.getItemStack(slot)));
        sendPacket(packet);
    }

    @Override
    public byte viewOpen(Container container) {
        if (idToContainer.inverse().containsKey(container)) {
            throw new IllegalStateException("The container " + container.getContainerType() + " have been opened by this viewer");
        }

        var assignedId = assignContainerId();
        sendContainerOpenPacket(assignedId, container);
        registerOpenedContainer(assignedId, container);

        var containerType = container.getContainerType();
        // We should send the container's contents to the client if the container is not held by the entity
        if (this.controlledEntity.getContainer(containerType) == null) {
            viewContents(container);
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
        var packet = new ContainerOpenPacket();
        packet.setId(assignedId);
        packet.setType(ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType());
        switch (container) {
            case BlockContainer blockContainer -> packet.setBlockPosition(NetworkHelper.toNetwork(blockContainer.getBlockPos()));
            case FakeContainerImpl fakeContainer -> packet.setBlockPosition(NetworkHelper.toNetwork(fakeContainer.getFakeBlockPos(this)));
            default -> {
                var location = this.controlledEntity.getLocation();
                packet.setBlockPosition(Vector3i.from(location.x(), location.y(), location.z()));
            }
        }
        this.sendPacket(packet);
    }

    @Override
    public void viewClose(Container container) {
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
        var packet = new ContainerClosePacket();
        packet.setId(assignedId);
        packet.setType(ContainerNetworkInfo.getInfo(container.getContainerType()).toNetworkType());
        if (!this.containerClosedByClient) {
            // Field `serverInitiated` determines whether the server force-closed the container. If this value is
            // not set correctly, the client may ignore the packet and respond with a `PacketViolationWarningPacket`.
            packet.setServerInitiated(true);
        } else {
            this.containerClosedByClient = false;
        }
        sendPacket(packet);
    }

    @Override
    public void viewContainerData(Container container, int property, int value) {
        var assignedId = idToContainer.inverse().get(container);
        if (assignedId == null) {
            throw new IllegalStateException("This viewer did not open the container " + container.getContainerType());
        }

        var packet = new ContainerSetDataPacket();
        packet.setWindowId(assignedId);
        packet.setProperty(property);
        packet.setValue(value);

        sendPacket(packet);
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
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(this.controlledEntity.getRuntimeId());
        packet.setAction(BossEventPacket.Action.CREATE);
        packet.setTitle(bossBar.getTitle());
        packet.setHealthPercentage(bossBar.getProgress());
        packet.setDarkenSky(bossBar.isDarkenSky() ? 1 : 0);
        packet.setColor(bossBar.getColor().ordinal());
        packet.setOverlay(bossBar.getStyle().ordinal());
        sendPacket(packet);
    }

    @Override
    public void clearBossBar() {
        var packet = new BossEventPacket();
        packet.setBossUniqueEntityId(this.controlledEntity.getRuntimeId());
        packet.setAction(BossEventPacket.Action.REMOVE);
        sendPacket(packet);
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

        var packet = new ModalFormRequestPacket();
        packet.setFormId(id);
        packet.setFormData(form.toJson());
        sendPacket(packet);

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
        sendPacket(new ClientboundCloseFormPacket());
        this.forms.invalidateAll();
    }

    @Override
    public void displayScoreboard(Scoreboard scoreboard, DisplaySlot slot) {
        var packet1 = new SetDisplayObjectivePacket();
        packet1.setDisplaySlot(slot.getSlotName());
        packet1.setObjectiveId(scoreboard.getObjectiveName());
        packet1.setDisplayName(scoreboard.getDisplayName());
        packet1.setCriteria(scoreboard.getCriteriaName());
        packet1.setSortOrder(scoreboard.getSortOrder().ordinal());
        sendPacket(packet1);

        // Client won't storage the score of a scoreboard,so we should send the score to client
        var packet2 = new SetScorePacket();
        packet2.setInfos(
                scoreboard.getLines().values()
                        .stream()
                        .map(this::toNetworkScoreInfo)
                        .filter(Objects::nonNull)
                        .toList()
        );
        packet2.setAction(SetScorePacket.Action.SET);
        sendPacket(packet2);

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
        var packet = new SetDisplayObjectivePacket();
        packet.setDisplaySlot(slot.getSlotName());
        packet.setObjectiveId("");
        packet.setDisplayName("");
        packet.setCriteria("");
        packet.setSortOrder(SortOrder.ASCENDING.ordinal());
        sendPacket(packet);

        if (slot == DisplaySlot.BELOW_NAME) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    @Override
    public void removeScoreboard(Scoreboard scoreboard) {
        var packet = new RemoveObjectivePacket();
        packet.setObjectiveId(scoreboard.getObjectiveName());

        sendPacket(packet);
    }

    @Override
    public void removeScoreboardLine(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.REMOVE);
        var networkInfo = toNetworkScoreInfo(line);
        if (networkInfo != null) {
            packet.getInfos().add(networkInfo);
        }
        sendPacket(packet);

        var scorer = new PlayerScorer(this);
        if (line.getScorer().equals(scorer) && line.getScoreboard().getViewers(DisplaySlot.BELOW_NAME).contains(this)) {
            this.controlledEntity.setScoreTag(null);
        }
    }

    @Override
    public void updateScore(ScoreboardLine line) {
        var packet = new SetScorePacket();
        packet.setAction(SetScorePacket.Action.SET);
        var networkInfo = toNetworkScoreInfo(line);
        if (networkInfo != null) {
            packet.getInfos().add(networkInfo);
        }
        sendPacket(packet);

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

    protected void sendAttribute(AttributeData attributeData) {
        var packet = new UpdateAttributesPacket();
        packet.setRuntimeEntityId(this.controlledEntity.getRuntimeId());
        packet.getAttributes().add(attributeData);
        sendPacket(packet);
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
            var packet = new CommandOutputPacket();
            packet.setType(CommandOutputType.ALL_OUTPUT);
            packet.setCommandOriginData(new CommandOriginData(CommandOriginType.PLAYER, this.loginData.getUuid(), "", 0));
            for (var output : outputs) {
                packet.getMessages().add(new CommandOutputMessage(
                        // Indicates if the output message was one of a successful command execution
                        status != CommandResult.FAIL_STATUS,
                        I18n.get().tr(this.loginData.getLangCode(), output.str(), output.args()),
                        new String[0]
                ));
            }
            packet.setSuccessCount(status);
            sendPacket(packet);
        } else {
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
        var packet = new TextPacket();
        packet.setType(type);
        packet.setXuid(this.loginData.getXuid());
        packet.setMessage(message);
        sendPacket(packet);
    }

    @Override
    public void sendToast(String title, String content) {
        ToastRequestPacket pk = new ToastRequestPacket();
        pk.setTitle(title);
        pk.setContent(content);
        sendPacket(pk);
    }

    @Override
    public void sendTitle(String title) {
        var pk = new SetTitlePacket();
        pk.setText(title);
        pk.setType(SetTitlePacket.Type.TITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        sendPacket(pk);
    }

    @Override
    public void sendSubtitle(String subtitle) {
        var pk = new SetTitlePacket();
        pk.setText(subtitle);
        pk.setType(SetTitlePacket.Type.SUBTITLE);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        sendPacket(pk);
    }

    @Override
    public void sendActionBar(String actionBar) {
        var pk = new SetTitlePacket();
        pk.setText(actionBar);
        pk.setType(SetTitlePacket.Type.ACTIONBAR);
        pk.setXuid("");
        pk.setPlatformOnlineId("");
        sendPacket(pk);
    }

    @Override
    public void setTitleSettings(int fadeInTime, int duration, int fadeOutTime) {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.TIMES);
        pk.setFadeInTime(fadeInTime);
        pk.setFadeOutTime(fadeOutTime);
        pk.setStayTime(duration);
        sendPacket(pk);
    }

    @Override
    public void resetTitleSettings() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.RESET);
        sendPacket(pk);
    }

    @Override
    public void clearTitle() {
        var pk = new SetTitlePacket();
        pk.setType(SetTitlePacket.Type.CLEAR);
        sendPacket(pk);
    }

    @Override
    public void sendPacket(Object p) {
        if (!(p instanceof BedrockPacket packet)) {
            return;
        }

        if (!getClientState().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(this, packet);
        if (!event.call()) {
            return;
        }

        this.session.sendPacket(event.getPacket());
    }

    @Override
    public void sendPacketImmediately(Object p) {
        if (!(p instanceof BedrockPacket packet)) {
            return;
        }

        if (!getClientState().canHandlePackets()) {
            return;
        }

        var event = new PacketSendEvent(this, packet);
        if (!event.call()) {
            return;
        }

        this.session.sendPacketImmediately(event.getPacket());
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
                flags, CommandPermission.ANY, aliases, List.of(), overloads.toArray(CommandOverloadData[]::new)
        );
    }

    @Override
    public void disconnect(@MayContainTrKey String reason) {
        if (!this.packetProcessorHolder.setClientState(ClientState.DISCONNECTED)) {
            log.warn("Trying to disconnect a player who is already disconnected!");
            return;
        }

        String translatedReason;
        if (this.packetProcessorHolder.getLastClientState().ordinal() >= ClientState.LOGGED_IN.ordinal()) {
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
        return packetProcessorHolder.getClientState();
    }

    public void setClientState(ClientState state) {
        this.packetProcessorHolder.setClientState(state);
    }

    @Override
    public ClientState getLastClientState() {
        return packetProcessorHolder.getLastClientState();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return this.session.getSocketAddress();
    }

    protected void onDisconnect(String disconnectReason) {
        new PlayerDisconnectEvent(this, disconnectReason).call();
        closeAllOpenedContainers();
        ((AllayPlayerManager) Server.getInstance().getPlayerManager()).removePlayer(this);
    }

    @Override
    public int getPing() {
        var rakServerChannel = (RakServerChannel) session.getPeer().getChannel().parent();
        var childChannel = rakServerChannel.getChildChannel(session.getSocketAddress());
        var rakSessionCodec = childChannel.rakPipeline().get(RakSessionCodec.class);
        return (int) rakSessionCodec.getPing();
    }

    @Override
    public void viewPlayerPermission(Player player) {
        var packet = new UpdateAbilitiesPacket();

        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        packet.setUniqueEntityId(entity.getRuntimeId());
        // The command permissions set here are actually not very useful. Their main function is to allow OPs to have quick command options.
        // If this player does not have specific command permissions, the command description won't even be sent to the client
        packet.setCommandPermission(entity.hasPermission(Permissions.ABILITY_OPERATOR_COMMAND_QUICK_BAR).asBoolean() ? CommandPermission.GAME_DIRECTORS : CommandPermission.ANY);
        // PlayerPermissions is the permission level of the player as it shows up in the player list built up using the PlayerList packet
        packet.setPlayerPermission(calculatePlayerPermission(entity));

        var layer = new AbilityLayer();
        layer.setLayerType(AbilityLayer.Type.BASE);
        layer.getAbilitiesSet().addAll(Arrays.asList(Ability.values()));
        layer.getAbilityValues().addAll(calculateAbilities(entity));
        // NOTICE: this shouldn't be changed
        layer.setWalkSpeed((float) DEFAULT_SPEED.calculate());
        layer.setFlySpeed((float) player.getFlySpeed().calculate());
        layer.setVerticalFlySpeed((float) player.getVerticalFlySpeed().calculate());
        packet.getAbilityLayers().add(layer);

        sendPacket(packet);

        if (player == this) {
            this.shouldSendCommands = true;
        }
    }

    protected EnumSet<Ability> calculateAbilities(EntityPlayer player) {
        var gameMode = player.getGameMode();

        var abilities = EnumSet.noneOf(Ability.class);
        abilities.add(Ability.TELEPORT);
        abilities.add(Ability.WALK_SPEED);
        abilities.add(Ability.FLY_SPEED);
        abilities.add(Ability.VERTICAL_FLY_SPEED);

        if (gameMode != GameMode.SPECTATOR) {
            abilities.add(Ability.BUILD);
            abilities.add(Ability.MINE);
            abilities.add(Ability.DOORS_AND_SWITCHES);
            abilities.add(Ability.OPEN_CONTAINERS);
            abilities.add(Ability.ATTACK_PLAYERS);
            abilities.add(Ability.ATTACK_MOBS);
        } else {
            abilities.add(Ability.NO_CLIP);
            abilities.add(Ability.FLYING);
        }

        if (gameMode == GameMode.CREATIVE) {
            abilities.add(Ability.INSTABUILD);
        }

        var tristate = player.hasPermission(Permissions.ABILITY_FLY);
        if (tristate.asBoolean() || (tristate != Tristate.FALSE && (gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR))) {
            abilities.add(Ability.MAY_FLY);
        }

        if (player.isFlying() && abilities.contains(Ability.MAY_FLY)) {
            abilities.add(Ability.FLYING);
        }

        return abilities;
    }

    protected PlayerPermission calculatePlayerPermission(EntityPlayer player) {
        if (!player.isActualPlayer() || Server.getInstance().getPlayerManager().isOperator(player.getController())) {
            return PlayerPermission.OPERATOR;
        }

        return PlayerPermission.MEMBER;
    }

    @Override
    public void viewPlayerListChange(Collection<Player> players, boolean add) {
        var packet = new PlayerListPacket();
        packet.setAction(add ? PlayerListPacket.Action.ADD : PlayerListPacket.Action.REMOVE);
        for (var player : players) {
            packet.getEntries().add(buildEntry(player));
        }
        sendPacket(packet);
    }

    protected PlayerListPacket.Entry buildEntry(Player player) {
        var entry = new PlayerListPacket.Entry(player.getLoginData().getUuid());
        var entity = Preconditions.checkNotNull(player.getControlledEntity());
        entry.setEntityId(entity.getRuntimeId());
        entry.setName(player.getOriginName());
        entry.setXuid(player.getLoginData().getXuid());
        entry.setPlatformChatId(player.getLoginData().getDeviceInfo().deviceName());
        entry.setBuildPlatform(player.getLoginData().getDeviceInfo().device().getId());
        entry.setSkin(SkinConvertor.toSerializedSkin(player.getLoginData().getSkin()));
        entry.setTrustedSkin(AllayServer.getSettings().resourcePackSettings().trustAllSkins());
        entry.setColor(new Color(player.getOriginName().hashCode() & 0xFFFFFF));
        return entry;
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
            viewPlayerPermission(this);
        }
    }

    @Override
    public void setVerticalFlySpeed(Speed verticalFlySpeed) {
        if (!this.verticalFlySpeed.equals(verticalFlySpeed)) {
            this.verticalFlySpeed = verticalFlySpeed;
            viewPlayerPermission(this);
        }
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
        var packet = new TransferPacket();
        packet.setAddress(ip);
        packet.setPort(port);
        sendPacket(packet);
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
        if (logOffWorld == null || logOffWorld.getDimension(playerData.getDimension()) == null) {
            // The world or dimension where the player logged off doesn't exist, fallback to the global spawn point
            dimension = (AllayDimension) server.getWorldPool().getGlobalSpawnPoint().dimension();
            currentPos = new org.joml.Vector3f(server.getWorldPool().getGlobalSpawnPoint());

            // The old pos stored in player's nbt is invalid, and we should replace it with the new one!
            var builder = playerData.getNbt().toBuilder();
            writeVector3f(builder, "Pos", currentPos);
            playerData.setNbt(builder.build());

            // Save new player data back to storage
            playerManager.getPlayerStorage().savePlayerData(this.loginData.getUuid(), playerData);
        } else {
            dimension = (AllayDimension) logOffWorld.getDimension(playerData.getDimension());
            currentPos = readVector3f(playerData.getNbt(), "Pos");
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

        this.packetProcessorHolder.setClientState(ClientState.SPAWNED);
        dimension.addPlayer(this);
        playerManager.addPlayer(this);

        startGame(dimension.getWorld(), playerData, dimension);

        sendPacket(NetworkData.ITEM_REGISTRY_PACKET.get());
        sendPacket(NetworkData.CREATIVE_CONTENT_PACKET.get());
        sendPacket(NetworkData.AVAILABLE_ENTITY_IDENTIFIERS_PACKET.get());
        sendPacket(NetworkData.BIOME_DEFINITION_LIST_PACKET.get());
        sendPacket(NetworkData.CRAFTING_DATA_PACKET.get());
        sendPacket(NetworkData.TRIM_DATA_PACKET.get());
    }

    /**
     * Sends {@link StartGamePacket} to the client.
     */
    @MultiVersion(version = "1.21.80 - 1.21.90", details = "AuthoritativeMovementMode is explicitly set to ensure compatibility")
    @MultiVersion(version = "*", details = "MultiVersionHelper is used")
    protected void startGame(World spawnWorld, PlayerData playerData, Dimension dimension) {
        var helper = session.getPeer().getCodecHelper();
        helper.setItemDefinitions(SimpleDefinitionRegistry.<ItemDefinition>builder().addAll(NetworkData.ITEM_DEFINITIONS.get()).build());
        helper.setBlockDefinitions(SimpleDefinitionRegistry.<BlockDefinition>builder().addAll(NetworkData.BLOCK_DEFINITIONS.get()).build());

        var packet = new StartGamePacket();

        packet.getGamerules().addAll(NetworkHelper.toNetwork(spawnWorld.getWorldData().getGameRules().getGameRules()));
        packet.setUniqueEntityId(this.controlledEntity.getRuntimeId());
        packet.setRuntimeEntityId(this.controlledEntity.getRuntimeId());
        packet.setPlayerGameType(GameType.from(playerData.getNbt().getInt("GameType", NetworkHelper.toNetwork(spawnWorld.getWorldData().getGameMode()).ordinal())));
        var loc = this.controlledEntity.getLocation();
        var worldSpawn = spawnWorld.getWorldData().getSpawnPoint();
        packet.setDefaultSpawn(Vector3i.from(worldSpawn.x(), worldSpawn.y(), worldSpawn.z()));
        packet.setPlayerPosition(Vector3f.from(loc.x(), loc.y() + 1.62, loc.z()));
        packet.setRotation(Vector2f.from(loc.pitch(), loc.yaw()));
        // We don't send world seed to the client for security reason
        packet.setSeed(0L);
        packet.setDimensionId(dimension.getDimensionInfo().dimensionId());
        // 0 - limit 1 - infinite
        // 2 - flat  3 - nether
        // 4 - end   5 - void
        packet.setGeneratorId(1);
        packet.setLevelGameType(NetworkHelper.toNetwork(spawnWorld.getWorldData().getGameMode()));
        packet.setDifficulty(spawnWorld.getWorldData().getDifficulty().ordinal());
        packet.setTrustingPlayers(true);
        packet.setLevelName(AllayServer.getSettings().genericSettings().motd());
        packet.setLevelId(AllayServer.getSettings().genericSettings().motd());
        packet.setDefaultPlayerPermission(PlayerPermission.valueOf(AllayServer.getSettings().genericSettings().defaultPermission()));
        packet.setServerChunkTickRange(AllayServer.getSettings().worldSettings().tickRadius());
        // VanillaVersion is the version of the game from which Vanilla features will be used
        packet.setVanillaVersion(ProtocolInfo.FEATURE_VERSION.getMinecraftVersion());
        // ServerEngine(aka.GameVersion) is the version of the game the server is running
        packet.setServerEngine(ProtocolInfo.FEATURE_VERSION.getMinecraftVersion());
        packet.setPremiumWorldTemplateId("00000000-0000-0000-0000-000000000000");
        packet.setInventoriesServerAuthoritative(true);
        packet.setServerAuthoritativeBlockBreaking(true);
        // MultiVersion: set to ensure compatibility for client below 1.21.90
        packet.setAuthoritativeMovementMode(AuthoritativeMovementMode.SERVER);
        packet.setCommandsEnabled(true);
        packet.setMultiplayerGame(true);
        packet.setBroadcastingToLan(true);
        packet.setMultiplayerCorrelationId(UUID.randomUUID().toString());
        packet.setXblBroadcastMode(GamePublishSetting.PUBLIC);
        packet.setPlatformBroadcastMode(GamePublishSetting.PUBLIC);
        packet.setBlockRegistryChecksum(0L);
        packet.setPlayerPropertyData(NbtMap.EMPTY);
        packet.setWorldTemplateId(new UUID(0, 0));
        packet.setEditorWorldType(WorldType.NON_EDITOR);
        packet.setChatRestrictionLevel(ChatRestrictionLevel.NONE);
        packet.setSpawnBiomeType(SpawnBiomeType.DEFAULT);
        packet.setCustomBiomeName("plains");
        packet.setEducationProductionId("");
        packet.setForceExperimentalGameplay(OptionalBoolean.empty());
        packet.setBlockNetworkIdsHashed(true);
        packet.setServerId("");
        packet.setWorldId("");
        packet.setScenarioId("");
        packet.setOwnerId("");
        packet.getExperiments().addAll(NetworkData.EXPERIMENT_DATA_LIST.get());
        MultiVersionHelper.adaptExperimentData(this, packet.getExperiments());
        sendPacket(packet);
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

        this.packetProcessorHolder.setClientState(ClientState.LOGGED_IN);
        sendPlayStatus(PlayStatusPacket.Status.LOGIN_SUCCESS);
        sendPacket(NetworkData.RESOURCE_PACKS_INFO_PACKET.get());
    }

    protected void sendPlayStatus(PlayStatusPacket.Status status) {
        var packet = new PlayStatusPacket();
        packet.setStatus(status);
        sendPacket(packet);
    }

    protected void sendCommands() {
        var packet = new AvailableCommandsPacket();
        Registries.COMMANDS.getContent().values().stream()
                .filter(command -> !command.isServerSideOnly() && this.controlledEntity.hasPermissions(command.getPermissions()))
                .forEach(command -> packet.getCommands().add(encodeCommand(command)));
        sendPacket(packet);
    }

    protected void sendHudElements() {
        var show = new SetHudPacket();
        var hide = new SetHudPacket();
        show.setVisibility(HudVisibility.RESET);
        hide.setVisibility(HudVisibility.HIDE);

        for (var element : HudElement.values()) {
            if (getHudElementVisibility(element)) {
                show.getElements().add(toNetwork(element));
            } else {
                hide.getElements().add(toNetwork(element));
            }
        }

        if (!show.getElements().isEmpty()) {
            sendPacket(show);
        }
        if (!hide.getElements().isEmpty()) {
            sendPacket(hide);
        }
    }

    @Override
    public void viewDialog(Dialog dialog, Entity entity) {
        this.dialog = new Pair<>(dialog, entity);

        var buttonJson = JSONUtils.to(NetworkHelper.toNetworkDialogButtons(dialog.getButtons()));
        var modelSettings = dialog.getModelSettings();
        var portraitOffsetJson = JSONUtils.to(Map.of("portrait_offsets", toNetwork(new ModelSettings(
                modelSettings.scale(),
                modelSettings.offset().add(0, NETWORK_OFFSETS.get().getOrDefault(entity.getEntityType(), 0.0f), 0, new Vector3d()),
                modelSettings.rotation()
        ))));

        var metadata = parseMetadata(entity);
        metadata.put(EntityDataTypes.NPC_DATA, portraitOffsetJson);

        var packet1 = new SetEntityDataPacket();
        packet1.setRuntimeEntityId(entity.getRuntimeId());
        packet1.setMetadata(metadata);
        sendPacket(packet1);

        var packet2 = new NpcDialoguePacket();
        packet2.setUniqueEntityId(entity.getRuntimeId());
        packet2.setAction(NpcDialoguePacket.Action.OPEN);
        packet2.setDialogue(dialog.getBody());
        packet2.setSceneName("default");
        packet2.setNpcName(dialog.getTitle());
        packet2.setActionJson(buttonJson);
        sendPacket(packet2);
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

        var packet = new NpcDialoguePacket();
        packet.setUniqueEntityId(this.dialog.right().getRuntimeId());
        packet.setDialogue("");
        packet.setSceneName("");
        packet.setNpcName("");
        packet.setActionJson("");
        packet.setAction(NpcDialoguePacket.Action.CLOSE);
        sendPacket(packet);

        this.dialog = null;
    }

    private class AllayPacketHandler implements BedrockPacketHandler {
        @Override
        public PacketSignal handlePacket(BedrockPacket packet) {
            if (!getClientState().canHandlePackets()) {
                return PacketSignal.HANDLED;
            }

            var event = new PacketReceiveEvent(AllayPlayer.this, packet);
            if (!event.call()) {
                return PacketSignal.HANDLED;
            }

            packet = event.getPacket();

            var processor = packetProcessorHolder.getProcessor(packet);
            if (processor == null) {
                log.warn("Received a packet which doesn't have correspond packet handler: {}, client status: {}", packet, getClientState());
                return PacketSignal.HANDLED;
            }

            World world = null;
            var time = Server.getInstance().getTick();
            if (controlledEntity != null) {
                world = controlledEntity.getWorld();
                if (world != null) {
                    // If the player is in any world, use world tick instead
                    time = world.getTick();
                }
            }

            if (processor.handleAsync(AllayPlayer.this, packet, time) != PacketSignal.HANDLED) {
                if (world == null) {
                    // Packet processors should make sure that PacketProcessor.handleSync()
                    // method won't be called if the player is not in any world
                    log.warn("Cannot handle sync packet {} for player {} which is not in any world!", packet.getPacketType().name(), AllayPlayer.this);
                    processor.handleSync(AllayPlayer.this, packet, time);
                } else {
                    ((AllayWorld) world).addSyncPacketToQueue(AllayPlayer.this, packet, time);
                }
            }

            return PacketSignal.HANDLED;
        }

        @Override
        public void onDisconnect(CharSequence seq) {
            var reason = seq.toString();
            if (!packetProcessorHolder.setClientState(ClientState.DISCONNECTED, false)) {
                // Failed to set the client state to DISCONNECTED from the current state. This usually
                // happens when the client has already been disconnected by calling disconnect(). This
                // is expected and should not be treated as an error
                return;
            }
            AllayPlayer.this.onDisconnect(reason);
        }
    }
}
