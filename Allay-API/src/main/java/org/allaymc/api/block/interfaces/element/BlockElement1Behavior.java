package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement1Behavior extends BlockBehavior {
  BlockType<BlockElement1Behavior> ELEMENT_1_TYPE = BlockTypeBuilder
          .builder(BlockElement1Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_1)
          .build();
}
