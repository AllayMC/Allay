package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement91Behavior extends BlockBehavior {
  BlockType<BlockElement91Behavior> ELEMENT_91_TYPE = BlockTypeBuilder
          .builder(BlockElement91Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_91)
          .build();
}
