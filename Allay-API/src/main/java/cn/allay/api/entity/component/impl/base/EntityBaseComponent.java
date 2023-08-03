package cn.allay.api.entity.component.impl.base;

import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.metadata.Metadata;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.math.Location3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
public interface EntityBaseComponent {
    @Inject
    EntityType<? extends Entity> getEntityType();

    @Inject
    Location3dc getLocation();

    @Inject
    void setLocation(Location3dc location);

    @Inject
    long getUniqueId();

    @Inject
    Metadata getMetadata();

    @Inject
    AABBdc getAABB();

    @Inject
    void setAABB(AABBd aabb);
}
