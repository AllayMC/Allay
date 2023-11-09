package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement33Behavior extends BlockBehavior {
  BlockType<BlockElement33Behavior> ELEMENT_33_TYPE = BlockTypeBuilder
          .builder(BlockElement33Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_33)
          .build();
}
