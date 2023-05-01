package cn.allay.block.impl;

import cn.allay.block.Block;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeBuilder;
import cn.allay.block.type.BlockTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaButton extends Block {
    BlockType<BlockAcaciaButton> TYPE = BlockTypeBuilder
            .builder(BlockAcaciaButton.class)
            .vanillaBlock(VanillaBlockId.ACACIA_BUTTON, true)
            .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT,
                    VanillaBlockPropertyTypes.FACING_DIRECTION)
            .addBasicComponents()
            .build().register(BlockTypeRegistry.getRegistry());
}
