package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement71Behavior extends BlockBehavior {
  BlockType<BlockElement71Behavior> ELEMENT_71_TYPE = BlockTypeBuilder
          .builder(BlockElement71Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_71)
          .build();
}
