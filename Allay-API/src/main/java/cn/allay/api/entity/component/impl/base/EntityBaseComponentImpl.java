package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/26 <br>
 * Allay Project <br>
 */
public class EntityBaseComponentImpl implements EntityBaseComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected static AtomicLong UNIQUE_ID_COUNTER = new AtomicLong(0);

    protected EntityType<? extends Entity> entityType;
    protected final Loc<Float> location;
    protected final long uniqueId = UNIQUE_ID_COUNTER.getAndIncrement();

    public EntityBaseComponentImpl(EntityType<? extends Entity> entityType, EntityInitInfo info) {
        this.entityType = entityType;
        this.location = info.location();
    }

    @Override
    @Impl
    public EntityType<? extends Entity> getEntityType() {
        return entityType;
    }

    @Override
    @Impl
    public FixedLoc<Float> getLocation() {
        return location;
    }

    @Override
    @Impl
    public long getUniqueId() {
        return uniqueId;
    }

    @Override
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
