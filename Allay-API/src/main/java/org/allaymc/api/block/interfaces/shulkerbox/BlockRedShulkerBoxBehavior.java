package org.allaymc.api.block.interfaces.shulkerbox;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedShulkerBoxBehavior extends BlockBehavior {
  BlockType<BlockRedShulkerBoxBehavior> RED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockRedShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
          .build();
}
