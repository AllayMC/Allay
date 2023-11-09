package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement29Behavior extends BlockBehavior {
  BlockType<BlockElement29Behavior> ELEMENT_29_TYPE = BlockTypeBuilder
          .builder(BlockElement29Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_29)
          .build();
}
