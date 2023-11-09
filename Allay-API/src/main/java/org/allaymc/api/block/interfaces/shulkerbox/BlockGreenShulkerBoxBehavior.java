package org.allaymc.api.block.interfaces.shulkerbox;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGreenShulkerBoxBehavior extends BlockBehavior {
  BlockType<BlockGreenShulkerBoxBehavior> GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockGreenShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
          .build();
}
