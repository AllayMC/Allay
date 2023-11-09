package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement102Behavior extends BlockBehavior {
  BlockType<BlockElement102Behavior> ELEMENT_102_TYPE = BlockTypeBuilder
          .builder(BlockElement102Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_102)
          .build();
}
