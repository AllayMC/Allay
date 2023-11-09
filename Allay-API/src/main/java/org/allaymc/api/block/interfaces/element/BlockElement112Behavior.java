package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement112Behavior extends BlockBehavior {
  BlockType<BlockElement112Behavior> ELEMENT_112_TYPE = BlockTypeBuilder
          .builder(BlockElement112Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_112)
          .build();
}
