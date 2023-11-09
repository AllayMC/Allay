package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement72Behavior extends BlockBehavior {
  BlockType<BlockElement72Behavior> ELEMENT_72_TYPE = BlockTypeBuilder
          .builder(BlockElement72Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_72)
          .build();
}
