package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement111Behavior extends BlockBehavior {
  BlockType<BlockElement111Behavior> ELEMENT_111_TYPE = BlockTypeBuilder
          .builder(BlockElement111Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_111)
          .build();
}
