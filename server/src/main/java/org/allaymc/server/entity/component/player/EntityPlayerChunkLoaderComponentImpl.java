package org.allaymc.server.entity.component.player;

import com.google.common.collect.BiMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
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
import org.allaymc.api.item.interfaces.ItemAirStack;
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
import org.allaymc.server.component.ComponentManager;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerChunkInRangeSendEvent;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.utils.ReflectionUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.BlockChangeEntry;
import org.cloudburstmc.protocol.bedrock.data.EmoteFlag;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.cloudburstmc.protocol.common.util.Preconditions;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.*;

import static org.allaymc.server.utils.Utils.toGameType;
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
                p.setHand(player.getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand().toNetworkItemData());
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
        var container = entity.getContainer(FullContainerType.PLAYER_INVENTORY);
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
        var container = entity.getContainer(FullContainerType.OFFHAND);
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
        var container = entity.getContainer(FullContainerType.ARMOR);
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
                    Vector3i.from((chunk.getX() << 4) + update.x(), update.y(), (chunk.getZ() << 4) + update.z()),
                    update.blockState()::blockStateHash, BLOCK_UPDATE_NETWORK, -1, BlockChangeEntry.MessageType.NONE
            );

            if (isExtraLayer) {
                packet.getExtraBlocks().add(entry);
            } else {
                packet.getStandardBlocks().add(entry);
            }
        }
    }

    @Override
    public void viewSound(Vector3dc pos, String sound, double volume, double pitch) {
        var packet = new PlaySoundPacket();
        packet.setSound(sound);
        packet.setVolume((float) volume);
        packet.setPitch((float) pitch);
        packet.setPosition(Vector3f.from(pos.x(), pos.y(), pos.z()));
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
}
