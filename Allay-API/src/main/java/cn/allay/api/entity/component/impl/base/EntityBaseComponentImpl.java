package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.interfaces.ComponentManager;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.Location3d;
import cn.allay.api.math.Location3dc;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public class EntityBaseComponentImpl<T extends Entity> implements EntityBaseComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected static AtomicLong UNIQUE_ID_COUNTER = new AtomicLong(0);
    protected final Location3d location;
    protected final long uniqueId = UNIQUE_ID_COUNTER.getAndIncrement();
    protected final Metadata metadata;
    protected EntityType<T> entityType;
    protected AABBdc aabb;
    @Manager
    protected ComponentManager<T> manager;

    public EntityBaseComponentImpl(EntityType<T> entityType,
                                   EntityInitInfo info) {
        this.entityType = entityType;
        this.aabb = entityType.updateAABB(manager.getComponentedObject());
        this.location = info.location();
        metadata = new Metadata();
        initMetadata();
    }

    private void initMetadata() {
        metadata.setInt(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY, (short) 400);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY_MAX, (short) 400);
//        metadata.setFloat(EntityDataTypes.SCALE, 1);
//        metadata.setFloat(EntityDataTypes.WIDTH, (float) (aabb.maxX() - aabb.minX()));
//        metadata.setFloat(EntityDataTypes.HEIGHT, (float) (aabb.maxY() - aabb.minY()));
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
                .putFloat("MaxX", (float) (aabb.maxX() - aabb.minX()))
                .putFloat("MaxY", (float) (aabb.maxY() - aabb.minY()))
                .putFloat("MaxZ", (float) (aabb.maxZ() - aabb.minZ()))
                .putFloat("PivotX", 0)
                .putFloat("PivotY", 0)
                .putFloat("PivotZ", 0)
                .build();
    }

    private void updateHitBoxAndCollisionBoxMetadata() {
        metadata.setNBT(EntityDataTypes.HITBOX, buildAABBTag());
        metadata.setVector3f(EntityDataTypes.COLLISION_BOX,
                Vector3f.from(
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
    public Location3dc getLocation() {
        return location;
    }

    @Override
    @Impl
    public void setLocation(Location3dc location) {
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

    @Override
    @Impl
    public AABBdc getAABB() {
        return aabb;
    }

    @Override
    @Impl
    public void setAABB(AABBd aabb) {
        this.aabb = aabb;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
