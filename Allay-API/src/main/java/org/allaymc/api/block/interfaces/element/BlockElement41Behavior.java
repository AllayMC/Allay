package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement41Behavior extends BlockBehavior {
  BlockType<BlockElement41Behavior> ELEMENT_41_TYPE = BlockTypeBuilder
          .builder(BlockElement41Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_41)
          .build();
}
