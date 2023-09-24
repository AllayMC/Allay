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
public interface BlockStoneBlockSlab3Behavior extends BlockBehavior {
    BlockType<BlockStoneBlockSlab3Behavior> STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab3Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3)
            .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
