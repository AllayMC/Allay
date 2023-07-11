package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.vector.Loc3f;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent {
    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    Loc3f getLocation();

    @Inject
    void setLocation(Loc3f location);

    @Inject
    long getUniqueId();

    @Inject
    Metadata getMetadata();
}
