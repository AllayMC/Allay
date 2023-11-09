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
public interface BlockBambooBlockBehavior extends BlockBehavior {
  BlockType<BlockBambooBlockBehavior> BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBambooBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
