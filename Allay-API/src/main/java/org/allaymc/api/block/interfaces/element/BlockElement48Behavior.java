package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement48Behavior extends BlockBehavior {
  BlockType<BlockElement48Behavior> ELEMENT_48_TYPE = BlockTypeBuilder
          .builder(BlockElement48Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_48)
          .build();
}
