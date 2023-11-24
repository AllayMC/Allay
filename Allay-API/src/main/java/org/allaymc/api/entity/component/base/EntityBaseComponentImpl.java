package org.allaymc.api.entity.component.base;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.event.EntityLoadNBTEvent;
import org.allaymc.api.entity.event.EntitySaveNBTEvent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.entity.metadata.Metadata;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.math.location.Location3fc;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.abs;
import static org.cloudburstmc.protocol.bedrock.packet.MoveEntityDeltaPacket.Flag.*;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityBaseComponentImpl<T extends Entity> implements EntityBaseComponent {

    @ComponentIdentifier
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");
    protected static AtomicLong UNIQUE_ID_COUNTER = new AtomicLong(0);

    protected final Location3f location;
    protected final Location3f locLastSent = new Location3f(0, 0, 0, null);
    protected final long uniqueId = UNIQUE_ID_COUNTER.getAndIncrement();
    protected final Metadata metadata;
    @Manager
    protected ComponentManager<T> manager;
    @ComponentedObject
    protected T thisEntity;
    @Dependency(soft = true)
    @Nullable
    protected EntityAttributeComponent attributeComponent;
    protected EntityType<T> entityType;
    protected AABBfc aabb;
    protected Map<Long, EntityPlayer> viewers = new Long2ObjectOpenHashMap<>();
    protected Map<EffectType, EffectInstance> effects = new HashMap<>();
    protected Vector3f motion = new Vector3f();
    protected boolean onGround = true;
    protected boolean willBeRemovedNextTick = false;
    protected boolean willBeAddedNextTick = false;
    protected boolean spawned;

    public EntityBaseComponentImpl(EntityInitInfo<T> info, AABBfc aabb) {
        this.location = new Location3f(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, info.dimension());
        this.entityType = info.getEntityType();
        this.aabb = aabb;
        this.metadata = new Metadata();
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        loadNBT(((EntityInitInfo<T>) initInfo).nbt());
        initMetadata();
    }

    protected void initMetadata() {
        metadata.setInt(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY, (short) 400);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY_MAX, (short) 400);
        updateHitBoxAndCollisionBoxMetadata();
        metadata.setFlag(EntityFlag.HAS_GRAVITY, true);
        metadata.setFlag(EntityFlag.HAS_COLLISION, true);
        metadata.setFlag(EntityFlag.CAN_CLIMB, true);
        metadata.setFlag(EntityFlag.BREATHING, true);
    }

    private NbtMap buildAABBTag() {
        return NbtMap.builder()
                .putFloat("MinX", 0)
                .putFloat("MinY", 0)
                .putFloat("MinZ", 0)
                .putFloat("MaxX", aabb.maxX() - aabb.minX())
                .putFloat("MaxY", aabb.maxY() - aabb.minY())
                .putFloat("MaxZ", aabb.maxZ() - aabb.minZ())
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
    }

    private void updateHitBoxAndCollisionBoxMetadata() {
        metadata.setNBT(EntityDataTypes.HITBOX, buildAABBTag());
        metadata.setVector3f(EntityDataTypes.COLLISION_BOX,
                org.cloudburstmc.math.vector.Vector3f.from(
                        aabb.maxX() - aabb.minX(),
                        aabb.maxY() - aabb.minY(),
                        aabb.maxZ() - aabb.minZ())
        );
    }

    @Override
    public EntityType<? extends Entity> getEntityType() {
        return entityType;
    }

    @Override
    public Location3fc getLocation() {
        return location;
    }

    @Override
    public Dimension getDimension() {
        return location.dimension;
    }

    @Override
    public World getWorld() {
        return location.dimension.getWorld();
    }

    @Override
    public void removeEntity() {
        getDimension().getEntityUpdateService().removeEntity(thisEntity);
    }

    @Override
    public boolean willBeRemovedNextTick() {
        return willBeRemovedNextTick;
    }

    @Override
    @ApiStatus.Internal
    public void setWillBeRemovedNextTick(boolean willBeRemovedNextTick) {
        this.willBeRemovedNextTick = willBeRemovedNextTick;
    }

    @Override
    public boolean isSpawned() {
        return spawned;
    }

    @Override
    @ApiStatus.Internal
    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    @Override
    public boolean willBeAddedNextTick() {
        return willBeAddedNextTick;
    }

    @Override
    @ApiStatus.Internal
    public void setWillBeAddedNextTick(boolean willBeAddedNextTick) {
        this.willBeAddedNextTick = willBeAddedNextTick;
    }

    @Override
    @ApiStatus.Internal
    public void setLocationAndCheckChunk(Location3fc newLoc) {
        checkChunk(this.location, newLoc, this.location.dimension == null);
        setLocation(newLoc);
    }

    protected void setLocation(Location3fc location) {
        // Calculate fall distance
        if (!onGround) {
            fallDistance -= location.y() - this.location.y();
        }
        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setDimension(location.dimension());
    }

    protected void checkChunk(Location3fc oldLoc, Location3fc newLoc, boolean currentDimIsNull) {
        var oldChunkX = (int) oldLoc.x() >> 4;
        var oldChunkZ = (int) oldLoc.z() >> 4;
        var newChunkX = (int) newLoc.x() >> 4;
        var newChunkZ = (int) newLoc.z() >> 4;
        var oldChunk = this.location.dimension().getChunkService().getChunk(oldChunkX, oldChunkZ);
        var newChunk = newLoc.dimension().getChunkService().getChunk(newChunkX, newChunkZ);
        if (!currentDimIsNull && (oldChunkX != newChunkX || oldChunkZ != newChunkZ)) {
            if (oldChunk != null) oldChunk.removeEntity(thisEntity.getUniqueId());
            else log.debug("Old chunk {} {} is null while moving entity!", oldChunkX, oldChunkZ);
        }
        if (newChunk != null) newChunk.addEntity(thisEntity);
        else {
            // Moving into an unloaded chunk is not allowed. Because the entity is held by the chunk, moving to an unloaded chunk will result in the loss of the entity
            log.debug("New chunk {} {} is null while moving entity!", newChunkX, newChunkZ);
        }
    }

    @Override
    public void teleport(Location3fc location) {
        Objects.requireNonNull(location.dimension());
        if (this.location.dimension == null) {
            log.warn("Trying to teleport an entity whose dimension is null! Entity: {}", thisEntity);
            return;
        }
        if (!this.spawned) {
            log.warn("Trying to teleport an entity which is not spawned! Entity: {}", thisEntity);
            return;
        }
        if (this.location.dimension == location.dimension()) {
            // Teleporting in the current same dimension, and we just need to move the entity to the new coordination
            setLocationAndCheckChunk(location);
            broadcastMoveToViewers(location, true);
        } else {
            // Teleporting to another dimension, there will be more works to be done
            // TODO: maybe needs more works
            location.dimension().getEntityUpdateService().removeEntity(thisEntity, () -> {
                location.dimension().getChunkService().getChunkImmediately((int) location.x() >> 4, (int) location.z() >> 4);
                setLocationAndCheckChunk(location);
            });
            location.dimension().getEntityUpdateService().addEntity(thisEntity);
        }
    }

    @Override
    public long getUniqueId() {
        return uniqueId;
    }

    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public void sendEntityData(EntityDataType<?>... dataTypes) {
        if (viewers.isEmpty()) return;
        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(uniqueId);
        for (EntityDataType<?> type : dataTypes) {
            pk.getMetadata().put(type, metadata.getEntityDataMap().get(type));
        }
        pk.setTick(this.getWorld().getTick());
        sendPacketToViewers(pk);
    }

    @Override
    public void sendEntityFlags(EntityFlag... flags) {
        if (viewers.isEmpty()) return;
        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(uniqueId);
        for (EntityFlag flag : flags) {
            pk.getMetadata().setFlag(flag, metadata.getFlag(flag));
        }
        pk.setTick(this.getWorld().getTick());
        sendPacketToViewers(pk);
    }

    @Override
    public AABBfc getAABB() {
        return aabb;
    }

    @Override
    public void setAABB(AABBf aabb) {
        this.aabb = aabb;
        updateHitBoxAndCollisionBoxMetadata();
        sendEntityData(EntityDataTypes.HITBOX, EntityDataTypes.COLLISION_BOX);
    }

    @Override
    public void setHasEntityCollision(boolean hasEntityCollision) {
        metadata.setFlag(EntityFlag.HAS_COLLISION, hasEntityCollision);
        sendEntityFlags(EntityFlag.HAS_COLLISION);
    }

    @Override
    public @UnmodifiableView Map<Long, EntityPlayer> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    public Vector3fc getMotion() {
        return motion;
    }

    @Override
    public void setMotion(Vector3fc motion) {
        this.motion = new Vector3f(motion);
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (fallDistance > 0) {
            onFall();
        }
    }

    @Override
    public void setHasGravity(boolean hasGravity) {
        metadata.setFlag(EntityFlag.HAS_GRAVITY, hasGravity);
        sendEntityFlags(EntityFlag.HAS_GRAVITY);
    }

    @Override
    public void spawnTo(EntityPlayer player) {
        var pk = createSpawnPacket();
        player.sendPacket(pk);
        viewers.put(player.getUniqueId(), player);
    }

    @Override
    public void despawnFrom(EntityPlayer player) {
        var pk = new RemoveEntityPacket();
        pk.setUniqueEntityId(uniqueId);
        player.sendPacket(pk);
        viewers.remove(player.getUniqueId());
    }

    @Override
    public void despawnFromAll() {
        viewers.values().forEach(this::despawnFrom);
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var addEntityPacket = new AddEntityPacket();
        addEntityPacket.setRuntimeEntityId(uniqueId);
        addEntityPacket.setUniqueEntityId(uniqueId);
        addEntityPacket.setIdentifier(entityType.getIdentifier().toString());
        addEntityPacket.setPosition(org.cloudburstmc.math.vector.Vector3f.from(location.x(), location.y() + getBaseOffset(), location.z()));
        addEntityPacket.setMotion(org.cloudburstmc.math.vector.Vector3f.from(motion.x(), motion.y(), motion.z()));
        addEntityPacket.setRotation(Vector2f.from(location.pitch(), location.yaw()));
        addEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
        return addEntityPacket;
    }

    @Override
    public void sendPacketToViewers(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacket(packet));
    }

    @Override
    public void sendPacketToViewersImmediately(BedrockPacket packet) {
        viewers.values().forEach(client -> client.handleChunkPacketImmediately(packet));
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc) {
        broadcastMoveToViewers(newLoc, false);
    }

    @Override
    public void broadcastMoveToViewers(Location3fc newLoc, boolean teleporting) {
        var pk = new MoveEntityDeltaPacket();
        pk.setRuntimeEntityId(getUniqueId());
        var moveFlags = computeMoveFlags(newLoc);
        pk.getFlags().addAll(moveFlags);
        if (moveFlags.contains(HAS_X)) {
            pk.setX(newLoc.x());
            locLastSent.x = newLoc.x();
        }
        if (moveFlags.contains(HAS_Y)) {
            pk.setY(newLoc.y());
            locLastSent.y = newLoc.y();
        }
        if (moveFlags.contains(HAS_Z)) {
            pk.setZ(newLoc.z());
            locLastSent.z = newLoc.z();
        }
        if (moveFlags.contains(HAS_PITCH)) {
            pk.setPitch((float) newLoc.pitch());
            locLastSent.pitch = newLoc.pitch();
        }
        if (moveFlags.contains(HAS_YAW)) {
            pk.setYaw((float) newLoc.yaw());
            locLastSent.yaw = newLoc.yaw();
        }
        if (moveFlags.contains(HAS_HEAD_YAW)) {
            pk.setHeadYaw((float) newLoc.headYaw());
            locLastSent.headYaw = newLoc.headYaw();
        }
        if (onGround) pk.getFlags().add(ON_GROUND);
        if (teleporting) pk.getFlags().add(TELEPORTING);
        sendPacketToViewers(pk);
    }

    protected Set<MoveEntityDeltaPacket.Flag> computeMoveFlags(Location3fc newLoc) {
        var flags = EnumSet.noneOf(MoveEntityDeltaPacket.Flag.class);
        var settings = Server.getInstance().getServerSettings().entitySettings().physicsEngineSettings();
        var diffPositionThreshold = settings.diffPositionThreshold();
        var diffRotationThreshold = settings.diffRotationThreshold();
        if (abs(locLastSent.x() - newLoc.x()) > diffPositionThreshold) flags.add(HAS_X);
        if (abs(locLastSent.y() - newLoc.y()) > diffPositionThreshold) flags.add(HAS_Y);
        if (abs(locLastSent.z() - newLoc.z()) > diffPositionThreshold) flags.add(HAS_Z);
        if (abs(locLastSent.yaw() - newLoc.yaw()) > diffRotationThreshold) flags.add(HAS_YAW);
        if (abs(locLastSent.pitch() - newLoc.pitch()) > diffRotationThreshold) flags.add(HAS_PITCH);
        if (enableHeadYaw() && abs(locLastSent.headYaw() - newLoc.headYaw()) > diffRotationThreshold)
            flags.add(HAS_HEAD_YAW);
        return flags;
    }

    @Override
    public NbtMap saveNBT() {
        var builder = NbtMap.builder();
        var event = new EntitySaveNBTEvent(builder);
        manager.callEvent(event);
        builder.putString("identifier", entityType.getIdentifier().toString())
                .putCompound("Pos",
                        NbtMap.builder()
                                .putFloat("x", location.x())
                                .putFloat("y", location.y())
                                .putFloat("z", location.z())
                                .build())
                .putCompound("Rotation",
                        NbtMap.builder()
                                .putFloat("yaw", (float) location.yaw())
                                .putFloat("pitch", (float) location.pitch())
                                .build())
                .putCompound("Motion",
                        NbtMap.builder()
                                .putFloat("dx", motion.x())
                                .putFloat("dy", motion.y())
                                .putFloat("dz", motion.z())
                                .build())
                .putBoolean("OnGround", onGround);
        if (attributeComponent != null) {
            //TODO: use component event
            builder.putList(
                    "Attributes",
                    NbtType.COMPOUND,
                    attributeComponent.saveAttributes()
            );
        }
        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        var event = new EntityLoadNBTEvent(nbt);
        manager.callEvent(event);
        if (attributeComponent != null && nbt.containsKey("Attributes")) {
            var attributes = nbt.getList("Attributes", NbtType.COMPOUND);
            for (NbtMap attribute : attributes) {
                attributeComponent.setAttribute(AttributeType.fromNBT(attribute));
            }
        }
        if (nbt.containsKey("Pos")) {
            NbtMap pos = nbt.getCompound("Pos");
            location.set(
                    pos.getFloat("x"),
                    pos.getFloat("y"),
                    pos.getFloat("z")
            );
        }
        if (nbt.containsKey("Rotation")) {
            NbtMap rotation = nbt.getCompound("Rotation");
            location.setYaw(rotation.getFloat("yaw"));
            location.setPitch(rotation.getFloat("pitch"));
        }
        if (nbt.containsKey("Motion")) {
            NbtMap motion = nbt.getCompound("Motion");
            this.motion.set(
                    motion.getFloat("dx"),
                    motion.getFloat("dy"),
                    motion.getFloat("dz")
            );
        }
        if (nbt.containsKey("OnGround")) {
            onGround = nbt.getBoolean("OnGround");
        }
    }

    protected float fallDistance = 0f;

    @Override
    public float getFallDistance() {
        return fallDistance;
    }

    @Override
    public void onFall() {
        //TODO: fall damage
    }

    @Override
    public void addEffect(EffectInstance effectInstance) {
        var old = effects.put(effectInstance.getType(), effectInstance);
        effectInstance.getType().onAdd(thisEntity, effectInstance);
        if (old == null) calculateEffectColor();
        var mobEffectPk = new MobEffectPacket();
        mobEffectPk.setRuntimeEntityId(uniqueId);
        mobEffectPk.setEffectId(effectInstance.getType().getId());
        mobEffectPk.setAmplifier(effectInstance.getAmplifier());
        mobEffectPk.setParticles(effectInstance.isVisible());
        mobEffectPk.setDuration(effectInstance.getDuration());
        mobEffectPk.setEvent(old == null ? MobEffectPacket.Event.ADD : MobEffectPacket.Event.MODIFY);
        sendPacketToViewers(mobEffectPk);
    }

    @Override
    public void removeEffect(EffectType effectType) {
        var removed = effects.remove(effectType);
        if (removed != null) {
            effectType.onRemove(thisEntity, removed);
            calculateEffectColor();
            var mobEffectPk = new MobEffectPacket();
            mobEffectPk.setRuntimeEntityId(uniqueId);
            mobEffectPk.setEffectId(effectType.getId());
            mobEffectPk.setEvent(MobEffectPacket.Event.REMOVE);
            sendPacketToViewers(mobEffectPk);
        }
    }

    protected void calculateEffectColor() {
        int[] color = new int[3];
        int count = 0;
        for (var effectInstance : this.effects.values()) {
            if (effectInstance.isVisible()) {
                var c = effectInstance.getType().getColor();
                color[0] += c.getRed() * (effectInstance.getAmplifier() + 1);
                color[1] += c.getGreen() * (effectInstance.getAmplifier() + 1);
                color[2] += c.getBlue() * (effectInstance.getAmplifier() + 1);
                count += effectInstance.getAmplifier() + 1;
            }
        }

        if (count > 0) {
            int r = (color[0] / count) & 0xff;
            int g = (color[1] / count) & 0xff;
            int b = (color[2] / count) & 0xff;

            this.metadata.setInt(EntityDataTypes.EFFECT_COLOR, (r << 16) + (g << 8) + b);
            this.metadata.setByte(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
        } else {
            this.metadata.setInt(EntityDataTypes.EFFECT_COLOR, 0);
            this.metadata.setByte(EntityDataTypes.EFFECT_AMBIENCE, (byte) 0);
        }

        sendEntityData(EntityDataTypes.EFFECT_COLOR, EntityDataTypes.EFFECT_AMBIENCE);
    }
}
