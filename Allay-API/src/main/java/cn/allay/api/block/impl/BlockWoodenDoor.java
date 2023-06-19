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
public interface BlockWoodenDoor extends Block {
  BlockType<BlockWoodenDoor> TYPE = BlockTypeBuilder
          .builder(BlockWoodenDoor.class)
          .vanillaBlock(VanillaBlockId.WOODEN_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();
}
