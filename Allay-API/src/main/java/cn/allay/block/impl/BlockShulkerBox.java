package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockShulkerBox extends Block {
    BlockType<BlockShulkerBox> TYPE = BlockTypeBuilder
            .builder(BlockShulkerBox.class)
            .vanillaBlock(VanillaBlockId.SHULKER_BOX, true)
            .withProperties(VanillaBlockPropertyTypes.COLOR)
            .addBasicComponents()
            .build();
}
