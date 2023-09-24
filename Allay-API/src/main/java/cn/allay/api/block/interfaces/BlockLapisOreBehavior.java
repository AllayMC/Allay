package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLapisOreBehavior extends BlockBehavior {
    BlockType<BlockLapisOreBehavior> LAPIS_ORE_TYPE = BlockTypeBuilder
            .builder(BlockLapisOreBehavior.class)
            .vanillaBlock(VanillaBlockId.LAPIS_ORE)
            .build();
}
