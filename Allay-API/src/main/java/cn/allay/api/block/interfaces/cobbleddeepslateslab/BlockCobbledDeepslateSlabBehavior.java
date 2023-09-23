package cn.allay.api.block.interfaces.cobbleddeepslateslab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateSlabBehavior extends BlockBehavior {
    BlockType<BlockCobbledDeepslateSlabBehavior> COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
