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
public interface BlockSpruceButton extends Block {
    BlockType<BlockSpruceButton> TYPE = BlockTypeBuilder
            .builder(BlockSpruceButton.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON, true)
            .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build();
}
