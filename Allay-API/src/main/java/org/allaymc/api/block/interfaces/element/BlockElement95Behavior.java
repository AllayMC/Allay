package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement95Behavior extends BlockBehavior {
  BlockType<BlockElement95Behavior> ELEMENT_95_TYPE = BlockTypeBuilder
          .builder(BlockElement95Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_95)
          .build();
}
