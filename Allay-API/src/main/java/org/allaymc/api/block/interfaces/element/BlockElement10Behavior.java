package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement10Behavior extends BlockBehavior {
  BlockType<BlockElement10Behavior> ELEMENT_10_TYPE = BlockTypeBuilder
          .builder(BlockElement10Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_10)
          .build();
}
