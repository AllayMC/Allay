package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Impl;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.EntityComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.location.FixedLoc;
import cn.allay.api.math.location.Loc;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/26 <br>
 * Allay Project <br>
 */
public class EntityBaseComponentImpl implements EntityBaseComponent, EntityComponentImpl {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_base_component");

    protected EntityType<? extends Entity> entityType;
    protected final Loc<Float> location;

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
    public Identifier getIdentifier() {
        return IDENTIFIER;
    }
}
