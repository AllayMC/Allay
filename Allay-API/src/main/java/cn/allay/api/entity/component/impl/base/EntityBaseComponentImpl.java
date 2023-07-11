package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.vector.Loc3f;
import cn.allay.api.math.vector.MutableLoc3f;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public class EntityBaseComponentImpl implements EntityBaseComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected static AtomicLong UNIQUE_ID_COUNTER = new AtomicLong(0);
    protected final MutableLoc3f location;
    protected final long uniqueId = UNIQUE_ID_COUNTER.getAndIncrement();
    protected final Metadata metadata;
    protected EntityType<? extends Entity> entityType;

    public EntityBaseComponentImpl(EntityType<? extends Entity> entityType,
                                   EntityInitInfo info) {
        this.entityType = entityType;
        this.location = info.location().mut();
        metadata = new Metadata();
        initMetadata();
    }

    private void initMetadata() {
        metadata.setInt(EntityDataTypes.PLAYER_INDEX, 0);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY, (short) 400);
        metadata.setShort(EntityDataTypes.AIR_SUPPLY_MAX, (short) 400);
        metadata.setFloat(EntityDataTypes.SCALE, 1);
        metadata.setFloat(EntityDataTypes.WIDTH, 0.6f);//TODO
        metadata.setFloat(EntityDataTypes.HEIGHT, 1.8f);//TODO
        metadata.setFlag(EntityFlag.HAS_GRAVITY, true);
        metadata.setFlag(EntityFlag.HAS_COLLISION, true);
        metadata.setFlag(EntityFlag.CAN_CLIMB, true);
        metadata.setFlag(EntityFlag.BREATHING, true);
    }

    @Override
    @Impl
    public EntityType<? extends Entity> getEntityType() {
        return entityType;
    }

    @Override
    @Impl
    public MutableLoc3f getLocation() {
        return location;
    }

    @Override
    @Impl
    public void setLocation(Loc3f location) {
        this.location.setLocation(location);
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
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
