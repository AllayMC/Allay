package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement40Behavior extends BlockBehavior {
  BlockType<BlockElement40Behavior> ELEMENT_40_TYPE = BlockTypeBuilder
          .builder(BlockElement40Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_40)
          .build();
}
