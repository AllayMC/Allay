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
public interface BlockLightBlockBehavior extends BlockBehavior {
    BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockLightBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHT_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
            .build();
}
