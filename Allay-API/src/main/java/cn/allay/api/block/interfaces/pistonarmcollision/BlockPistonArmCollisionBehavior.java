package cn.allay.api.block.interfaces.pistonarmcollision;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPistonArmCollisionBehavior extends BlockBehavior {
    BlockType<BlockPistonArmCollisionBehavior> PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
            .builder(BlockPistonArmCollisionBehavior.class)
            .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
