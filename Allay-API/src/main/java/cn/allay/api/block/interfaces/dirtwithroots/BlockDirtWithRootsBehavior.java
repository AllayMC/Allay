package cn.allay.api.block.interfaces.dirtwithroots;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDirtWithRootsBehavior extends BlockBehavior {
    BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockDirtWithRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS)
            .build();
}
