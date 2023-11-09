package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement61Behavior extends BlockBehavior {
  BlockType<BlockElement61Behavior> ELEMENT_61_TYPE = BlockTypeBuilder
          .builder(BlockElement61Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_61)
          .build();
}
