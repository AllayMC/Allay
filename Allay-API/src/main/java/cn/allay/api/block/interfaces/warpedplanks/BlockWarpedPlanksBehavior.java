package cn.allay.api.block.interfaces.warpedplanks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedPlanksBehavior extends BlockBehavior {
    BlockType<BlockWarpedPlanksBehavior> WARPED_PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockWarpedPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_PLANKS)
            .build();
}
