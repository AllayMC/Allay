package cn.allay.api.block.interfaces.hardglass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardGlassBehavior extends BlockBehavior {
    BlockType<BlockHardGlassBehavior> HARD_GLASS_TYPE = BlockTypeBuilder
            .builder(BlockHardGlassBehavior.class)
            .vanillaBlock(VanillaBlockId.HARD_GLASS)
            .build();
}
