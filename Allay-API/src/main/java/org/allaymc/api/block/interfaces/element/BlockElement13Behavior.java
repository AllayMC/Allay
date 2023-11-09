package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement13Behavior extends BlockBehavior {
  BlockType<BlockElement13Behavior> ELEMENT_13_TYPE = BlockTypeBuilder
          .builder(BlockElement13Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_13)
          .build();
}
