package cn.allay.api.block.interfaces.blackstonedoubleslab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlackstoneDoubleSlabBehavior extends BlockBehavior {
    BlockType<BlockBlackstoneDoubleSlabBehavior> BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockBlackstoneDoubleSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB)
            .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
