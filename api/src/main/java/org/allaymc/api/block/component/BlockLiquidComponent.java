package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.joml.Vector3ic;
import org.joml.primitives.AABBfc;

/**
 * @author daoge_cmd
 */
public interface BlockLiquidComponent extends BlockComponent {
    boolean isLiquidTouched(int x, int y, int z, BlockState blockState, AABBfc aabb);

    default boolean isLiquidTouched(Vector3ic pos, BlockState blockState, AABBfc aabb) {
        return isLiquidTouched(pos.x(), pos.y(), pos.z(), blockState, aabb);
    }

    default boolean isLiquidTouched(int x, int y, int z, BlockState blockState, Entity entity) {
        return isLiquidTouched(x, y, z, blockState, entity.getOffsetAABB());
    }

    default boolean isLiquidTouched(Vector3ic pos, BlockState blockState, Entity entity) {
        return isLiquidTouched(pos.x(), pos.y(), pos.z(), blockState, entity);
    }

    AABBfc getLiquidAABB(int x, int y, int z, BlockState blockState);

    default AABBfc getLiquidAABB(Vector3ic pos, BlockState blockState) {
        return getLiquidAABB(pos.x(), pos.y(), pos.z(), blockState);
    }
}
