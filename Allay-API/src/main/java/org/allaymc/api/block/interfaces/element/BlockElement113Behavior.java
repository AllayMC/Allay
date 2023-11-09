package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement113Behavior extends BlockBehavior {
  BlockType<BlockElement113Behavior> ELEMENT_113_TYPE = BlockTypeBuilder
          .builder(BlockElement113Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_113)
          .build();
}
