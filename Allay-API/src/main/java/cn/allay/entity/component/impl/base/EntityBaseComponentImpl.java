package cn.allay.entity.component.impl.base;

import cn.allay.component.annotation.Impl;
import cn.allay.entity.Entity;
import cn.allay.entity.component.EntityComponentImpl;
import cn.allay.entity.type.EntityInitInfo;
import cn.allay.entity.type.EntityType;
import cn.allay.identifier.Identifier;
import cn.allay.math.location.Loc;
import cn.allay.math.location.LocRO;

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
    public LocRO<Float> getLocation() {
        return location;
    }

    @Override
    public Identifier getNamespaceId() {
        return IDENTIFIER;
    }
}
