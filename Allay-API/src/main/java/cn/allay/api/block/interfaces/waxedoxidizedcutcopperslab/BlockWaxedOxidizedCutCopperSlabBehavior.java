package cn.allay.api.block.interfaces.waxedoxidizedcutcopperslab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedOxidizedCutCopperSlabBehavior extends BlockBehavior {
    BlockType<BlockWaxedOxidizedCutCopperSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
            .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
