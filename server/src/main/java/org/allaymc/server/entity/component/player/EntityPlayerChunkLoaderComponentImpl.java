package org.allaymc.server.entity.component.player;

import com.google.common.collect.BiMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.debugshape.*;
import org.allaymc.api.debugshape.DebugShape;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.EntityPlayerChunkLoaderComponent;
import org.allaymc.api.entity.data.AnimateAction;
import org.allaymc.api.entity.data.EntityEvent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.*;
import org.allaymc.api.world.sound.*;
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerChunkInRangeSendEvent;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.*;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.*;

import static org.allaymc.server.utils.Utils.toGameType;
import static org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.*;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * @author daoge_cmd
 */
public class EntityPlayerChunkLoaderComponentImpl implements EntityPlayerChunkLoaderComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_world_viewer_component");

    // Constants used in UpdateSubChunkBlocksPacket
    protected static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    protected static final int BLOCK_UPDATE_NETWORK = 0b0010;
    protected static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    protected static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    /**
     * A map which contains the network offset of some entities. The network offset is the additional offset in
     * y coordinate when sent over network.This is mostly the case for older entities such as player and TNT.
     */
    protected static final Map<EntityType<?>, Float> NETWORK_OFFSETS;
    protected static final BiMap<EntityEvent, EntityEventType> EVENT_TYPE_MAP;
    protected static final BiMap<AnimateAction, AnimatePacket.Action> ANIMATE_ACTION_MAP;

    static {
        NETWORK_OFFSETS = new HashMap<>();
        NETWORK_OFFSETS.put(EntityTypes.PLAYER, 1.62f);
        NETWORK_OFFSETS.put(EntityTypes.FALLING_BLOCK, 0.49f);
        NETWORK_OFFSETS.put(EntityTypes.ITEM, 0.125f);
        NETWORK_OFFSETS.put(EntityTypes.TNT, 0.49f);

        EVENT_TYPE_MAP = ReflectionUtils.mapStaticFields(EntityEvent.class, org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType.class);
        ANIMATE_ACTION_MAP = ReflectionUtils.mapStaticFields(AnimateAction.class, AnimatePacket.Action.class);
    }

    @ComponentObject
    protected EntityPlayer thisPlayer;
    @Manager
    protected ComponentManager manager;
    @Dependency
    protected EntityBaseComponent baseComponent;
    @Dependency
    protected EntityPlayerNetworkComponentImpl networkComponent;

    @Getter
    protected int chunkLoadingRadius;
    @Getter
    @Setter
    protected int chunkMaxSendCountPerTick;

    public EntityPlayerChunkLoaderComponentImpl() {
        this.chunkLoadingRadius = Server.SETTINGS.worldSettings().viewDistance();
        this.chunkMaxSendCountPerTick = Server.SETTINGS.worldSettings().chunkMaxSendCountPerTick();
    }

    @Override
    public void viewEntity(Entity entity) {
        var l = entity.getLocation();
        var position = Vector3f.from(l.x(), l.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f), l.z());
        var motion = switch (entity) {
            case EntityPhysicsComponent physicsComponent -> {
                var m = physicsComponent.getMotion();
                yield Vector3f.from(m.x(), m.y(), m.z());
            }
            default -> Vector3f.ZERO;
        };
        BedrockPacket packet = switch (entity) {
            case EntityPlayer player -> {
                var loginData = player.getLoginData();
                var p = new AddPlayerPacket();
                p.setRuntimeEntityId(player.getRuntimeId());
                p.setUniqueEntityId(player.getUniqueId());
                p.setUuid(loginData.getUuid());
                p.setUsername(player.getOriginName());
                p.setPlatformChatId(loginData.getDeviceInfo().deviceId());
                // NOTICE: Player network offset is not used in AddPlayerPacket
                p.setPosition(Vector3f.from(l.x(), l.y(), l.z()));
                p.setMotion(motion);
                p.setRotation(Vector3f.from(l.pitch(), l.yaw(), l.headYaw()));
                p.setGameType(toGameType(player.getGameMode()));
                p.getMetadata().putAll(getMetadata(entity));
                p.setDeviceId(loginData.getDeviceInfo().deviceId());
                p.setHand(player.getContainer(ContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());
                yield p;
            }
            case EntityItem item -> {
                var p = new AddItemEntityPacket();
                p.setRuntimeEntityId(item.getRuntimeId());
                p.setUniqueEntityId(item.getUniqueId());
                p.setItemInHand(item.getItemStack().toNetworkItemData());
                p.setPosition(position);
                p.setMotion(motion);
                p.getMetadata().putAll(getMetadata(entity));
                yield p;
            }
            default -> {
                var p = new AddEntityPacket();
                p.setRuntimeEntityId(entity.getRuntimeId());
                p.setUniqueEntityId(entity.getUniqueId());
                p.setIdentifier(entity.getEntityType().getIdentifier().toString());
                p.setPosition(position);
                p.setMotion(motion);
                p.setRotation(Vector2f.from(l.pitch(), l.yaw()));
                p.getMetadata().putAll(getMetadata(entity));
                yield p;
            }
        };
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void removeEntity(Entity entity) {
        var packet = new RemoveEntityPacket();
        packet.setUniqueEntityId(entity.getUniqueId());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerGameMode(EntityPlayer player) {
        var gameMode = player.getGameMode();
        if (thisPlayer == player) {
            var packet1 = new SetPlayerGameTypePacket();
            packet1.setGamemode(toGameType(player.getGameMode()).ordinal());
            this.networkComponent.sendPacket(packet1);

            var packet2 = new UpdateAdventureSettingsPacket();
            packet2.setNoPvM(gameMode == GameMode.SPECTATOR);
            packet2.setNoMvP(gameMode == GameMode.SPECTATOR);
            packet2.setShowNameTags(gameMode != GameMode.SPECTATOR);
            packet2.setImmutableWorld(gameMode == GameMode.SPECTATOR);
            packet2.setAutoJump(true);
            this.networkComponent.sendPacket(packet2);
        } else {
            var packet = new UpdatePlayerGameTypePacket();
            packet.setGameType(toGameType(player.getGameMode()));
            packet.setEntityId(player.getRuntimeId());
            this.networkComponent.sendPacket(packet);
        }
    }

    @Override
    public void viewEntityLocation(Entity entity, Location3d locationLastSent, Location3dc newLocation, boolean teleporting) {
        BedrockPacket packet;
        if (Server.SETTINGS.entitySettings().physicsEngineSettings().useDeltaMovePacket()) {
            packet = createDeltaMovePacket(entity, locationLastSent, newLocation, teleporting);
        } else {
            packet = createAbsoluteMovePacket(entity, newLocation, teleporting);
        }
        this.networkComponent.sendPacket(packet);
    }

    protected BedrockPacket createAbsoluteMovePacket(Entity entity, Location3dc newLocation, boolean teleporting) {
        var packet = new MoveEntityAbsolutePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setPosition(Vector3f.from(newLocation.x(), newLocation.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f), newLocation.z()));
        packet.setRotation(Vector3f.from(newLocation.pitch(), newLocation.yaw(), newLocation.headYaw()));
        packet.setTeleported(teleporting);
        if (entity instanceof EntityPhysicsComponent physicsComponent) {
            packet.setOnGround(physicsComponent.isOnGround());
        }
        return packet;
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
            packet.setY((float) newLocation.y());
            locationLastSent.y = newLocation.y() + NETWORK_OFFSETS.getOrDefault(entity.getEntityType(), 0.0f);
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
            locationLastSent.yaw = newLocation.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            packet.setHeadYaw((float) newLocation.headYaw());
            locationLastSent.headYaw = newLocation.headYaw();
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
        var settings = Server.SETTINGS.entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (Math.abs(locationLastSent.x() - newLocation.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (Math.abs(locationLastSent.y() - newLocation.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (Math.abs(locationLastSent.z() - newLocation.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (Math.abs(locationLastSent.yaw() - newLocation.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (Math.abs(locationLastSent.pitch() - newLocation.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (entity.isHeadYawEnabled() && Math.abs(locationLastSent.headYaw() - newLocation.headYaw()) > diffRotationThreshold) {
            flags.add(HAS_HEAD_YAW);
        }
        return flags;
    }

    @Override
    public <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion) {
        var packet = new SetEntityMotionPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityMetadata(Entity entity) {
        var packet = new SetEntityDataPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setMetadata(getMetadata(entity));
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity) {
        var container = entity.getContainer(ContainerType.PLAYER_INVENTORY);
        var handSlot = container.getHandSlot();
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.PLAYER_INVENTORY);
        packet.setItem(container.getItemInHand().toNetworkItemData());
        packet.setInventorySlot(handSlot);
        packet.setHotbarSlot(handSlot);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity) {
        var container = entity.getContainer(ContainerType.OFFHAND);
        var packet = new MobEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setContainerId(UnopenedContainerId.OFFHAND);
        // Network slot index for offhand is 1, see FullContainerType.OFFHAND. Field `hotbarSlot` is unused
        packet.setInventorySlot(1);
        packet.setItem(container.getOffhand().toNetworkItemData());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity) {
        var container = entity.getContainer(ContainerType.ARMOR);
        var packet = new MobArmorEquipmentPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setBody(ItemAirStack.AIR_STACK.toNetworkItemData());
        packet.setHelmet(container.getHelmet().toNetworkItemData());
        packet.setChestplate(container.getChestplate().toNetworkItemData());
        packet.setLeggings(container.getLeggings().toNetworkItemData());
        packet.setBoots(container.getBoots().toNetworkItemData());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerSkin(EntityPlayer player) {
        var skin = player.getSkin();
        var packet = new PlayerSkinPacket();
        packet.setUuid(networkComponent.getLoginData().getUuid());
        packet.setSkin(skin);
        packet.setNewSkinName(skin.getSkinId());
        // It seems that old skin name is unused
        packet.setOldSkinName("");
        packet.setTrustedSkin(true);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityEvent(Entity entity, EntityEvent event, int data) {
        var packet = new EntityEventPacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setType(EVENT_TYPE_MAP.get(event));
        packet.setData(data);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityAction(Entity entity, AnimateAction action, double rowingTime) {
        var packet = new AnimatePacket();
        packet.setRuntimeEntityId(entity.getRuntimeId());
        packet.setAction(ANIMATE_ACTION_MAP.get(action));
        packet.setRowingTime((float) rowingTime);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewPlayerEmote(EntityPlayer player, UUID emoteId) {
        var packet = new EmotePacket();
        packet.setRuntimeEntityId(player.getRuntimeId());
        packet.setEmoteId(emoteId.toString());
        packet.getFlags().add(EmoteFlag.SERVER_SIDE);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect) {
        var packet = new MobEffectPacket();
        if (newEffect == null) {
            Preconditions.checkNotNull(oldEffect);
            // Effect is removed
            packet.setRuntimeEntityId(entity.getRuntimeId());
            packet.setEffectId(oldEffect.getType().getId());
            packet.setEvent(MobEffectPacket.Event.REMOVE);
        } else {
            packet.setRuntimeEntityId(entity.getRuntimeId());
            packet.setEffectId(newEffect.getType().getId());
            packet.setAmplifier(newEffect.getAmplifier());
            packet.setParticles(newEffect.isVisible());
            packet.setDuration(newEffect.getDuration());
            packet.setEvent(oldEffect == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        }
        this.networkComponent.sendPacket(packet);
    }

    protected EntityDataMap getMetadata(Entity entity) {
        return ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).getMetadata().getNetworkMetadata();
    }

    @Override
    public Location3dc getLocation() {
        // NOTICE: Don't call thisPlayer.getLocation() here which will cause stack over flow error
        return this.baseComponent.getLocation();
    }

    @Override
    public boolean isLoaderActive() {
        return thisPlayer.isSpawned();
    }

    @Override
    public void setChunkLoadingRadius(int radius) {
        this.chunkLoadingRadius = Math.min(radius, Server.SETTINGS.worldSettings().viewDistance());
        var packet = new ChunkRadiusUpdatedPacket();
        packet.setRadius(chunkLoadingRadius);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void onLoaderChunkPosChange() {
        var location = thisPlayer.getLocation();
        var packet = new NetworkChunkPublisherUpdatePacket();
        packet.setPosition(org.cloudburstmc.math.vector.Vector3i.from(location.x(), location.y(), location.z()));
        packet.setRadius(getChunkLoadingRadius() << 4);
        networkComponent.sendPacket(packet);
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
        // TODO: remove
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
        // TODO: remove
        this.networkComponent.sendPacketImmediately(packet);
    }

    @Override
    public void viewChunk(Chunk chunk) {
        this.networkComponent.sendPacket(createLevelChunkPacket(chunk));
        // This method will be called in non-ticking thread if async chunk sending is enabled. Let's
        // send the entities in this chunk to the player next tick in the main thread: use forEachEntitiesInChunk()
        // instead of forEachEntitiesInChunkImmediately()
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(chunk.getX(), chunk.getZ(), entity -> entity.spawnTo(thisPlayer));
        manager.callEvent(CPlayerChunkInRangeSendEvent.INSTANCE);
    }

    protected LevelChunkPacket createLevelChunkPacket(Chunk chunk) {
        var lcp = new LevelChunkPacket[1];
        chunk.applyOperation(unsafeChunk -> {
            if (Server.SETTINGS.worldSettings().useSubChunkSendingSystem()) {
                lcp[0] = createSubChunkLevelChunkPacket((AllayUnsafeChunk) unsafeChunk);
            } else {
                lcp[0] = createFullLevelChunkPacketChunk((AllayUnsafeChunk) unsafeChunk);
            }
        }, OperationType.READ, OperationType.READ);
        return lcp[0];
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

    @Override
    public void removeChunk(long chunkHash) {
        thisPlayer.getDimension().getEntityManager().forEachEntitiesInChunk(
                HashUtils.getXFromHashXZ(chunkHash),
                HashUtils.getZFromHashXZ(chunkHash),
                entity -> entity.despawnFrom(thisPlayer)
        );
    }

    @Override
    public void viewTime(int timeOfDay) {
        var packet = new SetTimePacket();
        packet.setTime(timeOfDay);
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewGameRules(GameRules gameRules) {
        var packet = new GameRulesChangedPacket();
        packet.getGameRules().addAll(((AllayGameRules) gameRules).toNetworkGameRuleData());
        this.networkComponent.sendPacket(packet);
    }

    @Override
    public void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState) {
        var packet = new UpdateBlockPacket();
        packet.setBlockPosition(MathUtils.toCBVec(pos));
        packet.setDataLayer(layer);
        packet.setDefinition(blockState::blockStateHash);
        packet.getFlags().add(UpdateBlockPacket.Flag.NETWORK);
        this.networkComponent.sendPacket(packet);
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

            this.networkComponent.sendPacket(packet);
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
        var pos = MathUtils.toCBVec(MathUtils.toVec3f(p));
        packet.setPosition(pos);
        packet.setIdentifier(":");
        packet.setExtraData(-1);
        packet.setRelativeVolumeDisabled(relative);

        switch (sound) {
            case SimpleSound.FIREWORK_LAUNCH -> packet.setSound(SoundEvent.LAUNCH);
            case SimpleSound.FIREWORK_HUGE_BLAST -> packet.setSound(SoundEvent.LARGE_BLAST);
            case SimpleSound.FIREWORK_BLAST -> packet.setSound(SoundEvent.BLAST);
            case SimpleSound.FIREWORK_TWINKLE -> packet.setSound(SoundEvent.TWINKLE);
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
            case SimpleSound.GLOW_INK_SAC_USED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INK_SACE_USED);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.DOOR_CRASH -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ZOMBIE_DOOR_CRASH);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
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
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.SIGN_WAXED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_ON);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.WAX_REMOVED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_WAX_OFF);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.COPPER_SCRAPED -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.PARTICLE_SCRAPE);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.POP -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_INFINITY_ARROW_PICKUP);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_ADD -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ADD);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_REMOVE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_REMOVE);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ITEM_FRAME_ROTATE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ITEMFRAME_ITEM_ROTATE);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_WARNING -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_WARNING);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.GHAST_SHOOT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_GHAST_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TNT -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_FUSE);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_LAND -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_LAND);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_USE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_USED);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.ANVIL_BREAK -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_ANVIL_BROKEN);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.FIRE_CHARGE -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_BLAZE_FIREBALL);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case SimpleSound.TOTEM -> {
                LevelEventPacket levelEvent = new LevelEventPacket();
                levelEvent.setType(LevelEvent.SOUND_TOTEM_USED);
                levelEvent.setPosition(pos.toFloat());
                this.networkComponent.sendPacket(levelEvent);
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
                this.networkComponent.sendPacket(levelEvent);
                return;
            }
            case EquipItemSound so -> packet.setSound(getEquipSound(so.itemType()));
            case NoteSound so -> {
                packet.setSound(SoundEvent.NOTE);
                // 假设Instrument.getIntValue()返回整数值
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
                    case START ->
                            packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_START : SoundEvent.CROSSBOW_LOADING_START);
                    case MIDDLE ->
                            packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_MIDDLE : SoundEvent.CROSSBOW_LOADING_MIDDLE);
                    case END ->
                            packet.setSound(so.quickCharge() ? SoundEvent.CROSSBOW_QUICK_CHARGE_END : SoundEvent.CROSSBOW_LOADING_END);
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
                this.networkComponent.sendPacket(playSound);
                return;
            }
            case CustomSound so -> {
                PlaySoundPacket playSound = new PlaySoundPacket();
                playSound.setSound(so.soundName());
                playSound.setPosition(pos);
                playSound.setVolume(so.volume());
                playSound.setPitch(so.pitch());
                this.networkComponent.sendPacket(playSound);
                return;
            }
            default -> throw new IllegalArgumentException("Unhandled sound type: " + sound.getClass().getSimpleName());
        }

        this.networkComponent.sendPacket(packet);
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
        var pos = MathUtils.toCBVec(MathUtils.toVec3f(p));
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
                this.networkComponent.sendPacket(pk);
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
            default ->
                    throw new IllegalArgumentException("Unhandled particle type: " + particle.getClass().getSimpleName());
        }
        this.networkComponent.sendPacket(packet);
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
        this.networkComponent.sendPacket(packet1);

        var packet2 = new LevelEventPacket();
        packet2.setPosition(Vector3f.ZERO);
        packet2.setType(LevelEvent.STOP_THUNDERSTORM);
        if (weather != Weather.RAIN) {
            packet2.setType(LevelEvent.START_THUNDERSTORM);
            packet2.setData(65535);
        }
        this.networkComponent.sendPacket(packet2);
    }

    @Override
    public void viewDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(toNetworkData(debugShape));
        networkComponent.sendPacket(packet);
    }

    @Override
    public void viewDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(toNetworkData(debugShape));
        }
        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(createRemovalNotice(debugShape));
        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(createRemovalNotice(debugShape));
        }
        networkComponent.sendPacket(packet);
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape createRemovalNotice(DebugShape shape) {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                shape.getId(), null, null,
                null, null, null,
                null, null, null,
                null, null, null, null
        );
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData(DebugShape shape) {
        return switch (shape) {
            case DebugArrow arrow -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    arrow.getId(), ARROW, MathUtils.toCBVec(arrow.getPosition()), arrow.getArrowHeadScale(),
                    null, null, arrow.getColor(),
                    null, null, MathUtils.toCBVec(arrow.getEndPosition()),
                    arrow.getArrowHeadLength(), arrow.getArrowHeadRadius(), arrow.getArrowHeadSegments()
            );
            case DebugBox box -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    box.getId(), BOX, MathUtils.toCBVec(box.getPosition()), box.getScale(),
                    null, null, box.getColor(),
                    null, MathUtils.toCBVec(box.getBoxBounds()), null,
                    null, null, null
            );
            case DebugCircle circle -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    circle.getId(), CIRCLE, MathUtils.toCBVec(circle.getPosition()), circle.getScale(),
                    null, null, circle.getColor(),
                    null, null, null,
                    null, null, circle.getSegments()
            );
            case DebugLine line -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    line.getId(), LINE, MathUtils.toCBVec(line.getPosition()), null,
                    null, null, line.getColor(),
                    null, null, MathUtils.toCBVec(line.getEndPosition()),
                    null, null, null
            );
            case DebugSphere sphere -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    sphere.getId(), SPHERE, MathUtils.toCBVec(sphere.getPosition()), sphere.getScale(),
                    null, null, sphere.getColor(),
                    null, null, null,
                    null, null, sphere.getSegments()
            );
            case DebugText text -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    text.getId(), TEXT, MathUtils.toCBVec(text.getPosition()), null,
                    null, null, text.getColor(),
                    text.getText(), null, null,
                    null, null, null
            );
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
    }
}
