package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement82Behavior extends BlockBehavior {
  BlockType<BlockElement82Behavior> ELEMENT_82_TYPE = BlockTypeBuilder
          .builder(BlockElement82Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_82)
          .build();
}
