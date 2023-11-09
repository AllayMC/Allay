package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement2Behavior extends BlockBehavior {
  BlockType<BlockElement2Behavior> ELEMENT_2_TYPE = BlockTypeBuilder
          .builder(BlockElement2Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_2)
          .build();
}
