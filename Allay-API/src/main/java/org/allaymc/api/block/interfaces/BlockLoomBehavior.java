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
public interface BlockLoomBehavior extends BlockBehavior {
  BlockType<BlockLoomBehavior> LOOM_TYPE = BlockTypeBuilder
          .builder(BlockLoomBehavior.class)
          .vanillaBlock(VanillaBlockId.LOOM)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION)
          .build();
}
