package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement5Behavior extends BlockBehavior {
  BlockType<BlockElement5Behavior> ELEMENT_5_TYPE = BlockTypeBuilder
          .builder(BlockElement5Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_5)
          .build();
}
