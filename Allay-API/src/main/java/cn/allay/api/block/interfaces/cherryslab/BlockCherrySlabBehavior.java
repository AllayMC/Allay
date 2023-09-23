package cn.allay.api.block.interfaces.cherryslab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCherrySlabBehavior extends BlockBehavior {
    BlockType<BlockCherrySlabBehavior> CHERRY_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockCherrySlabBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SLAB)
            .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
