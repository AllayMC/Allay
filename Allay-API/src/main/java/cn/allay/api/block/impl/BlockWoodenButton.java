package cn.allay.api.block.impl;

import cn.allay.api.block.Block;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * Author: daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWoodenButton extends Block {
  BlockType<BlockWoodenButton> TYPE = BlockTypeBuilder
          .builder(BlockWoodenButton.class)
          .vanillaBlock(VanillaBlockId.WOODEN_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();
}
