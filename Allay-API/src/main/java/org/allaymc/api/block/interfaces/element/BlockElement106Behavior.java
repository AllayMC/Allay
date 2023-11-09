package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement106Behavior extends BlockBehavior {
  BlockType<BlockElement106Behavior> ELEMENT_106_TYPE = BlockTypeBuilder
          .builder(BlockElement106Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_106)
          .build();
}
