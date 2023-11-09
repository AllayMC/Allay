package org.allaymc.api.block.interfaces.coral;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFireCoralBehavior extends BlockBehavior {
  BlockType<BlockFireCoralBehavior> FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockFireCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE_CORAL)
          .build();
}
