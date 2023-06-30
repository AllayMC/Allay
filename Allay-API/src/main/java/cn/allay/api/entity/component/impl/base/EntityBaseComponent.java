package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.FixedLoc;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent {
    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    FixedLoc<Float> getLocation();

    @Inject
    long getUniqueId();
}
