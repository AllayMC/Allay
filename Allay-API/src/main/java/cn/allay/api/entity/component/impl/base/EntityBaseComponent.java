package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.location.FixedLoc;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/26 <br>
 * Allay Project <br>
 */
public interface EntityBaseComponent {
    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    FixedLoc<Float> getLocation();
}
