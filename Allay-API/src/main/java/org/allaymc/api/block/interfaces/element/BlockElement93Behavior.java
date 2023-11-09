package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement93Behavior extends BlockBehavior {
  BlockType<BlockElement93Behavior> ELEMENT_93_TYPE = BlockTypeBuilder
          .builder(BlockElement93Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_93)
          .build();
}
