package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement14Behavior extends BlockBehavior {
  BlockType<BlockElement14Behavior> ELEMENT_14_TYPE = BlockTypeBuilder
          .builder(BlockElement14Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_14)
          .build();
}
