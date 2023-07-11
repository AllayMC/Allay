package cn.allay.api.math.aabb;

import lombok.EqualsAndHashCode;

/**
 * Allay Project 2023/3/25
 *
 * @author daoge_cmd
 */
record ImplAABB(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) implements AABB {
}
