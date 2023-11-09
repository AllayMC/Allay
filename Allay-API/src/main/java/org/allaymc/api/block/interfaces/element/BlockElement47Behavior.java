package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement47Behavior extends BlockBehavior {
  BlockType<BlockElement47Behavior> ELEMENT_47_TYPE = BlockTypeBuilder
          .builder(BlockElement47Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_47)
          .build();
}
