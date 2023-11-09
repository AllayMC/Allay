package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement20Behavior extends BlockBehavior {
  BlockType<BlockElement20Behavior> ELEMENT_20_TYPE = BlockTypeBuilder
          .builder(BlockElement20Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_20)
          .build();
}
