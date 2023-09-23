package cn.allay.api.block.interfaces.waxedcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedCopperBehavior> WAXED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_COPPER)
            .build();
}
