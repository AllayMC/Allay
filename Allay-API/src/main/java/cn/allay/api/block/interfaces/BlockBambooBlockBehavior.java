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
public interface BlockBambooBlockBehavior extends BlockBehavior {
    BlockType<BlockBambooBlockBehavior> BAMBOO_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBambooBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
