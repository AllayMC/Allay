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
public interface BlockGlowLichen extends Block {
  BlockType<BlockGlowLichen> TYPE = BlockTypeBuilder
          .builder(BlockGlowLichen.class)
          .vanillaBlock(VanillaBlockId.GLOW_LICHEN, true)
          .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();
}
