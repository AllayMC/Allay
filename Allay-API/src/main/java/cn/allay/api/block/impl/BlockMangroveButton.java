package cn.allay.api.block.impl;

import cn.allay.api.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.block.Block;
import cn.allay.api.block.data.VanillaBlockId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveButton extends Block {
    BlockType<BlockMangroveButton> TYPE = BlockTypeBuilder
            .builder(BlockMangroveButton.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_BUTTON, true)
            .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
