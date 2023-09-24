package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonFungusBehavior extends BlockBehavior {
    BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonFungusBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS)
            .build();
}
