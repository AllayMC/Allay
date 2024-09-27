package org.allaymc.server.block.component;

import org.allaymc.api.block.component.BlockLiquidComponent;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.server.component.annotation.Identifier;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

/**
 * @author daoge_cmd
 */
public class BlockLiquidComponentImpl implements BlockLiquidComponent {

    @Identifier
    public static final org.allaymc.api.utils.Identifier IDENTIFIER = new org.allaymc.api.utils.Identifier("minecraft:block_liquid_component");

    @Override
    public boolean isLiquidTouched(int x, int y, int z, BlockState blockState, AABBfc aabb) {
        var liquidAABB = getLiquidAABB(x, y, z, blockState);
        return liquidAABB.intersectsAABB(aabb);
    }

    @Override
    public AABBf getLiquidAABB(int x, int y, int z, BlockState blockState) {
        var liquidDepth = blockState.getPropertyValue(BlockPropertyTypes.LIQUID_DEPTH);
        return new AABBf(0, 0, 0, 1, 0.0625f * (liquidDepth + 1), 1).translate(x, y, z);
    }
}
