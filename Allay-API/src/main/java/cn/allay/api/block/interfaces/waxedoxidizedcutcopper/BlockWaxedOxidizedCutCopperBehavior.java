package cn.allay.api.block.interfaces.waxedoxidizedcutcopper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCutCopperBehavior extends BlockBehavior {
    BlockType<BlockWaxedOxidizedCutCopperBehavior> WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
}
