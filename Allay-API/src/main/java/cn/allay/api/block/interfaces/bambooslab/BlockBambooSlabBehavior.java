package cn.allay.api.block.interfaces.bambooslab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBambooSlabBehavior extends BlockBehavior {
    BlockType<BlockBambooSlabBehavior> BAMBOO_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBambooSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SLAB)
            .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
