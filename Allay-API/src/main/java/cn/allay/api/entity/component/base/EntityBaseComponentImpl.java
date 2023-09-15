package cn.allay.api.entity.component.base;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.attribute.AttributeType;
import cn.allay.api.entity.component.attribute.EntityAttributeComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.math.location.Location3fc;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

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

    @Manager
    protected ComponentManager<T> manager;

    @Dependency(soft = true)
    @Nullable
    protected EntityAttributeComponent attributeComponent;

    protected final Location3f location;
    protected final long uniqueId = UNIQUE_ID_COUNTER.getAndIncrement();
    protected final Metadata metadata;
    protected EntityType<T> entityType;
    protected AABBfc aabb;
    protected Function<T, AABBfc> aabbGetter;
    protected Map<Long, Client> viewers = new Long2ObjectOpenHashMap<>();
    protected Vector3f motion = new Vector3f();
    protected boolean onGround = true;

    public EntityBaseComponentImpl(EntityInitInfo<T> info, Function<T, AABBfc> aabbGetter) {
        Objects.requireNonNull(info.world(), "World cannot be null!");
        this.location = new Location3f(0, 0, 0, info.world());
        this.entityType = info.getEntityType();
        this.aabbGetter = aabbGetter;
        this.metadata = new Metadata();
        loadNBT(info.nbt());
    }

    @Override
    public void onInitFinish() {
        this.aabb = aabbGetter.apply(manager.getComponentedObject());
        initMetadata();
    }

    private void initMetadata() {
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
    @Impl
    public EntityType<? extends Entity> getEntityType() {
        return entityType;
    }

    @Override
    @Impl
    public Location3fc getLocation() {
        return location;
    }

    @Override
    @Impl
    @ApiStatus.Internal
    public void setLocation(Location3fc location) {
        var oldChunkX = (int) this.location.x >> 4;
        var oldChunkZ = (int) this.location.z >> 4;
        var newChunkX = (int) location.x() >> 4;
        var newChunkZ = (int) location.z() >> 4;
        if (oldChunkX != newChunkX || oldChunkZ != newChunkZ) {
            var oldChunk = location.world().getChunkService().getChunk(oldChunkX, oldChunkZ);
            var newChunk = location.world().getChunkService().getChunk(newChunkX, newChunkZ);
            if (newChunk != null) newChunk.addEntity(manager.getComponentedObject());
            else {
                log.warn("New chunk {} {} is null while moving entity!", newChunkX, newChunkZ);
                //不允许移动到未加载的区块中。因为entity引用由区块持有，移动到未加载的区块会导致entity丢失
                return;
            }
            if (oldChunk != null) oldChunk.removeEntity(uniqueId);
            else log.warn("Old chunk {} {} is null while moving entity!", oldChunkX, oldChunkZ);
        }
        this.location.set(location);
        this.location.setYaw(location.yaw());
        this.location.setHeadYaw(location.headYaw());
        this.location.setPitch(location.pitch());
        this.location.setWorld(location.world());
    }

    @Override
    @Impl
    public long getUniqueId() {
        return uniqueId;
    }

    @Override
    @Impl
    public Metadata getMetadata() {
        return metadata;
    }

    protected void sendEntityData(EntityDataType<?>... dataTypes) {
        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(uniqueId);
        for (EntityDataType<?> type : dataTypes) {
            pk.getMetadata().put(type, metadata.getEntityDataMap().get(type));
        }
        pk.setTick(location.world().getServer().getTicks());
        sendPacketToViewers(pk);
    }

    protected void sendEntityFlags(EntityFlag... flags) {
        var pk = new SetEntityDataPacket();
        pk.setRuntimeEntityId(uniqueId);
        for (EntityFlag flag : flags) {
            pk.getMetadata().setFlag(flag, metadata.getFlag(flag));
        }
        pk.setTick(location.world().getServer().getTicks());
        sendPacketToViewers(pk);
    }

    @Override
    @Impl
    public AABBfc getAABB() {
        return aabb;
    }

    @Override
    @Impl
    public void setAABB(AABBf aabb) {
        this.aabb = aabb;
        updateHitBoxAndCollisionBoxMetadata();
        sendEntityData(EntityDataTypes.HITBOX, EntityDataTypes.COLLISION_BOX);
    }

    @Override
    @Impl
    public void setHasCollision(boolean hasCollision) {
        metadata.setFlag(EntityFlag.HAS_COLLISION, hasCollision);
        sendEntityFlags(EntityFlag.HAS_COLLISION);
    }

    @Override
    @Impl
    public @UnmodifiableView Map<Long, Client> getViewers() {
        return Collections.unmodifiableMap(viewers);
    }

    @Override
    @Impl
    public Vector3fc getMotion() {
        return motion;
    }

    @Override
    @Impl
    public void setMotion(Vector3fc motion) {
        this.motion = new Vector3f(motion);
    }

    @Override
    @Impl
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    @Impl
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    @Impl
    public void setHasGravity(boolean hasGravity) {
        metadata.setFlag(EntityFlag.HAS_GRAVITY, hasGravity);
        sendEntityFlags(EntityFlag.HAS_GRAVITY);
    }

    @Override
    @Impl
    public void spawnTo(Client client) {
        var pk = createSpawnPacket();
        client.sendPacket(pk);
        viewers.put(client.getPlayerEntity().getUniqueId(), client);
    }

    @Override
    @Impl
    public void despawnFrom(Client client) {
        var pk = new RemoveEntityPacket();
        pk.setUniqueEntityId(uniqueId);
        client.sendPacket(pk);
        viewers.remove(client.getPlayerEntity().getUniqueId());
    }

    @Override
    @Impl
    public void despawnFromAll() {
        viewers.values().forEach(this::despawnFrom);
    }

    @Override
    @Impl
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
    @Impl
    public void sendPacketToViewers(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacket(packet));
    }

    @Override
    @Impl
    public void sendPacketToViewersImmediately(BedrockPacket packet) {
        viewers.values().forEach(client -> client.sendPacketImmediately(packet));
    }

    @Override
    @Impl
    public void broadcastMoveToViewers(Set<MoveEntityDeltaPacket.Flag> moveFlags, Location3fc newLoc) {
        var pk = new MoveEntityDeltaPacket();
        pk.setRuntimeEntityId(getUniqueId());
        pk.getFlags().addAll(moveFlags);
        pk.setX(newLoc.x());
        pk.setY(newLoc.y());
        pk.setZ(newLoc.z());
        pk.setPitch((float) newLoc.pitch());
        pk.setYaw((float) newLoc.yaw());
        pk.setHeadYaw((float) newLoc.headYaw());
        if (onGround) pk.getFlags().add(MoveEntityDeltaPacket.Flag.ON_GROUND);
        sendPacketToViewers(pk);
    }

    @Override
    @Impl
    public NbtMap saveNBT() {
        var builder = NbtMap.builder()
                .putString("identifier", entityType.getIdentifier().toString())
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
            builder.putList(
                    "Attributes",
                    NbtType.COMPOUND,
                    attributeComponent.saveAttributes()
            );
        }
        return builder.build();
    }

    @Override
    @Impl
    public void loadNBT(NbtMap nbt) {
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
}
