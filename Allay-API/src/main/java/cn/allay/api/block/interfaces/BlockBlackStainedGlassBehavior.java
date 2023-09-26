package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackStainedGlassBehavior extends BlockBehavior {
    BlockType<BlockBlackStainedGlassBehavior> BLACK_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockBlackStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACK_STAINED_GLASS)
            .build();
}
