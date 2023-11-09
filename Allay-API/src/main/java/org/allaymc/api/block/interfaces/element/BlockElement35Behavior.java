package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement35Behavior extends BlockBehavior {
  BlockType<BlockElement35Behavior> ELEMENT_35_TYPE = BlockTypeBuilder
          .builder(BlockElement35Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_35)
          .build();
}
