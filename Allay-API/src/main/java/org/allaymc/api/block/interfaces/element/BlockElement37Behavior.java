package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement37Behavior extends BlockBehavior {
  BlockType<BlockElement37Behavior> ELEMENT_37_TYPE = BlockTypeBuilder
          .builder(BlockElement37Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_37)
          .build();
}
