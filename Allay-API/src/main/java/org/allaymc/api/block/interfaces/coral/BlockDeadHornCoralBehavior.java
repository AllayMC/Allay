package org.allaymc.api.block.interfaces.coral;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadHornCoralBehavior extends BlockBehavior {
  BlockType<BlockDeadHornCoralBehavior> DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL)
          .build();
}
