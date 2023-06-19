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
public interface BlockMediumAmethystBud extends Block {
  BlockType<BlockMediumAmethystBud> TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBud.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();
}
