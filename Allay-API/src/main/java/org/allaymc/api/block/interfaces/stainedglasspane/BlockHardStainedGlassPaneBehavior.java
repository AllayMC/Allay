package org.allaymc.api.block.interfaces.stainedglasspane;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardStainedGlassPaneBehavior extends BlockBehavior {
  BlockType<BlockHardStainedGlassPaneBehavior> HARD_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassPaneBehavior.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .build();
}
