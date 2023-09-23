package cn.allay.api.block.interfaces.mossblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMossBlockBehavior extends BlockBehavior {
    BlockType<BlockMossBlockBehavior> MOSS_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMossBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MOSS_BLOCK)
            .build();
}
