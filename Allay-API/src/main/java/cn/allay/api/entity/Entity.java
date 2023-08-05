package cn.allay.api.entity;

import cn.allay.api.datastruct.aabbtree.HasAABB;
import cn.allay.api.datastruct.aabbtree.HasLongId;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;
import org.joml.primitives.AABBd;
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
    default AABBd copyAABBTo(AABBd dest) {
        var aabb = getAABB();
        dest.setMin(aabb.minX(), aabb.minY(), aabb.minZ());
        dest.setMax(aabb.maxX(), aabb.maxY(), aabb.maxZ());
        return dest;
    }
}
