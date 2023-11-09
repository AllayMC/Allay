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
public interface BlockCoralBlockBehavior extends BlockBehavior {
  BlockType<BlockCoralBlockBehavior> CORAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoralBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.CORAL_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
          .build();
}
