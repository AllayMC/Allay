package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement51Behavior extends BlockBehavior {
  BlockType<BlockElement51Behavior> ELEMENT_51_TYPE = BlockTypeBuilder
          .builder(BlockElement51Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_51)
          .build();
}
