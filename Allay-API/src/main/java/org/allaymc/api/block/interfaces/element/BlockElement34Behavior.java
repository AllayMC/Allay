package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement34Behavior extends BlockBehavior {
  BlockType<BlockElement34Behavior> ELEMENT_34_TYPE = BlockTypeBuilder
          .builder(BlockElement34Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_34)
          .build();
}
