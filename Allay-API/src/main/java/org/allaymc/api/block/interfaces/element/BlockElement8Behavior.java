package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement8Behavior extends BlockBehavior {
  BlockType<BlockElement8Behavior> ELEMENT_8_TYPE = BlockTypeBuilder
          .builder(BlockElement8Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_8)
          .build();
}
