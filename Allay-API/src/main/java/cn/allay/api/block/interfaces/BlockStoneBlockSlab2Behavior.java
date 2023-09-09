package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab2Behavior extends BlockBehavior {
    BlockType<BlockStoneBlockSlab2Behavior> STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab2Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2)
            .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
