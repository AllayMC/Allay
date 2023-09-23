package cn.allay.api.block.interfaces.stoneblockslab4;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStoneBlockSlab4Behavior extends BlockBehavior {
    BlockType<BlockStoneBlockSlab4Behavior> STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlab4Behavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4)
            .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
            .build();
}
