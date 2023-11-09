package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement17Behavior extends BlockBehavior {
  BlockType<BlockElement17Behavior> ELEMENT_17_TYPE = BlockTypeBuilder
          .builder(BlockElement17Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_17)
          .build();
}
