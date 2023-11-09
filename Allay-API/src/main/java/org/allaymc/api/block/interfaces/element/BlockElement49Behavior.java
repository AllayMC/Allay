package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement49Behavior extends BlockBehavior {
  BlockType<BlockElement49Behavior> ELEMENT_49_TYPE = BlockTypeBuilder
          .builder(BlockElement49Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_49)
          .build();
}
