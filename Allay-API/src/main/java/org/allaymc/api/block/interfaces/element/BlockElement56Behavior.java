package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement56Behavior extends BlockBehavior {
  BlockType<BlockElement56Behavior> ELEMENT_56_TYPE = BlockTypeBuilder
          .builder(BlockElement56Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_56)
          .build();
}
