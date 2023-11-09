package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement9Behavior extends BlockBehavior {
  BlockType<BlockElement9Behavior> ELEMENT_9_TYPE = BlockTypeBuilder
          .builder(BlockElement9Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_9)
          .build();
}
