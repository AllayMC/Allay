package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeStainedGlassBehavior extends BlockBehavior {
    BlockType<BlockOrangeStainedGlassBehavior> ORANGE_STAINED_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockOrangeStainedGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.ORANGE_STAINED_GLASS)
            .build();
}
