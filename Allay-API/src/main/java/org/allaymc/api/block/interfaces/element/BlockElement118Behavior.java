package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement118Behavior extends BlockBehavior {
  BlockType<BlockElement118Behavior> ELEMENT_118_TYPE = BlockTypeBuilder
          .builder(BlockElement118Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_118)
          .build();
}
