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
public interface BlockStoneBlockSlabBehavior extends BlockBehavior {
    BlockType<BlockStoneBlockSlabBehavior> STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
            .builder(BlockStoneBlockSlabBehavior.class)
            .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB)
            .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
            .build();
}
