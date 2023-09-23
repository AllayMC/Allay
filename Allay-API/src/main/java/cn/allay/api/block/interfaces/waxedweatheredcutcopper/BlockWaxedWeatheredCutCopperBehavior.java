package cn.allay.api.block.interfaces.waxedweatheredcutcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedWeatheredCutCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedWeatheredCutCopperBehavior> WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedWeatheredCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER)
            .build();
}
