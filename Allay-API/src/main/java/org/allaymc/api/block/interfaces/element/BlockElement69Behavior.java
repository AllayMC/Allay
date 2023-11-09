package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement69Behavior extends BlockBehavior {
  BlockType<BlockElement69Behavior> ELEMENT_69_TYPE = BlockTypeBuilder
          .builder(BlockElement69Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_69)
          .build();
}
