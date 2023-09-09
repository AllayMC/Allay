package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedMushroomBlockBehavior extends BlockBehavior {
    BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRedMushroomBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
            .build();
}
