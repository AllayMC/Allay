package org.allaymc.api.entity;

import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.world.service.HasAABB;
import org.allaymc.api.world.service.HasLongId;
import org.joml.primitives.AABBf;

/**
 * @author daoge_cmd
 */
public interface Entity extends
        EntityBaseComponent,
        HasAABB, HasLongId {

    @Override
    default AABBf copyOffsetAABBTo(AABBf dest) {
        var aabb = getOffsetAABB();
        dest.setMin(aabb.minX(), aabb.minY(), aabb.minZ());
        dest.setMax(aabb.maxX(), aabb.maxY(), aabb.maxZ());
        return dest;
    }

    @Override
    default long getLongId() {
        return getRuntimeId();
    }
}
