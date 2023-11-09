package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement68Behavior extends BlockBehavior {
  BlockType<BlockElement68Behavior> ELEMENT_68_TYPE = BlockTypeBuilder
          .builder(BlockElement68Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_68)
          .build();
}
