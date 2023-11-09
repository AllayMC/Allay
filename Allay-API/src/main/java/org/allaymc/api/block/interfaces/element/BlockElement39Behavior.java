package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement39Behavior extends BlockBehavior {
  BlockType<BlockElement39Behavior> ELEMENT_39_TYPE = BlockTypeBuilder
          .builder(BlockElement39Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_39)
          .build();
}
