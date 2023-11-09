package org.allaymc.api.block.interfaces.coral;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTubeCoralBehavior extends BlockBehavior {
  BlockType<BlockTubeCoralBehavior> TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockTubeCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.TUBE_CORAL)
          .build();
}
