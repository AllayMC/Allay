package org.allaymc.api.entity;

import org.allaymc.api.datastruct.aabbtree.HasAABB;
import org.allaymc.api.datastruct.aabbtree.HasLongId;
import org.allaymc.api.entity.component.common.EntityBaseComponent;
import org.joml.primitives.AABBf;

/**
 * Allay Project 2023/5/20
 *
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
