package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedstoneBlockBehavior extends BlockBehavior {
    BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK)
            .build();
}
