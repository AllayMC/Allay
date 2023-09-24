package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMelonBlockBehavior extends BlockBehavior {
    BlockType<BlockMelonBlockBehavior> MELON_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockMelonBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.MELON_BLOCK)
            .build();
}
