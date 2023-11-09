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
public interface BlockQuartzBlockBehavior extends BlockBehavior {
  BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
