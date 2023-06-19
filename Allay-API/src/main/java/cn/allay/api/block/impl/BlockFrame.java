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
public interface BlockFrame extends Block {
  BlockType<BlockFrame> TYPE = BlockTypeBuilder
          .builder(BlockFrame.class)
          .vanillaBlock(VanillaBlockId.FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT)
          .addBasicComponents()
          .build();
}
