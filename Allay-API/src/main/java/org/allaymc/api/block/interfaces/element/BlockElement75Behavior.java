package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement75Behavior extends BlockBehavior {
  BlockType<BlockElement75Behavior> ELEMENT_75_TYPE = BlockTypeBuilder
          .builder(BlockElement75Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_75)
          .build();
}
