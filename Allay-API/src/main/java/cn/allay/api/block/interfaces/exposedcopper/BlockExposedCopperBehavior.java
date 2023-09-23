package cn.allay.api.block.interfaces.exposedcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockExposedCopperBehavior extends BlockBehavior {
    BlockType<BlockExposedCopperBehavior> EXPOSED_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockExposedCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.EXPOSED_COPPER)
            .build();
}
