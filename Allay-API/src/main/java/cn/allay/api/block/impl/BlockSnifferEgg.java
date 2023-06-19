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
public interface BlockSnifferEgg extends Block {
  BlockType<BlockSnifferEgg> TYPE = BlockTypeBuilder
          .builder(BlockSnifferEgg.class)
          .vanillaBlock(VanillaBlockId.SNIFFER_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
          .addBasicComponents()
          .build();
}
