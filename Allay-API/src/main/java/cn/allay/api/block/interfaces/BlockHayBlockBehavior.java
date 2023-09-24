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
public interface BlockHayBlockBehavior extends BlockBehavior {
    BlockType<BlockHayBlockBehavior> HAY_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockHayBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.HAY_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
