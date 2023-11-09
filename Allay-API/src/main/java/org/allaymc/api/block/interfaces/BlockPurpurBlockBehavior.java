package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpurBlockBehavior extends BlockBehavior {
  BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockPurpurBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
