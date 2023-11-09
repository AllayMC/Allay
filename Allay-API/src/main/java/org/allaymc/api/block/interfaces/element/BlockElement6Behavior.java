package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement6Behavior extends BlockBehavior {
  BlockType<BlockElement6Behavior> ELEMENT_6_TYPE = BlockTypeBuilder
          .builder(BlockElement6Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_6)
          .build();
}
