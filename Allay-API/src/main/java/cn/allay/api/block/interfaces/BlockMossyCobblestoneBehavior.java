package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMossyCobblestoneBehavior extends BlockBehavior {
    BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
            .builder(BlockMossyCobblestoneBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE)
            .build();
}
