package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement101Behavior extends BlockBehavior {
  BlockType<BlockElement101Behavior> ELEMENT_101_TYPE = BlockTypeBuilder
          .builder(BlockElement101Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_101)
          .build();
}
