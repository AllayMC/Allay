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
public interface BlockSuspiciousSand extends Block {
  BlockType<BlockSuspiciousSand> TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousSand.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND, true)
          .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();
}
