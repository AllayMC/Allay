package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement108Behavior extends BlockBehavior {
  BlockType<BlockElement108Behavior> ELEMENT_108_TYPE = BlockTypeBuilder
          .builder(BlockElement108Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_108)
          .build();
}
