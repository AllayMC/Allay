package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement104Behavior extends BlockBehavior {
  BlockType<BlockElement104Behavior> ELEMENT_104_TYPE = BlockTypeBuilder
          .builder(BlockElement104Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_104)
          .build();
}
