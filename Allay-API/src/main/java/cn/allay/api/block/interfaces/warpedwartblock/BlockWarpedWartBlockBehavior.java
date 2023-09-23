package cn.allay.api.block.interfaces.warpedwartblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedWartBlockBehavior extends BlockBehavior {
    BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockWarpedWartBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK)
            .build();
}
