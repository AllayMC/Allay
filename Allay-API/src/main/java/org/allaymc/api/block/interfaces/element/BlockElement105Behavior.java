package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement105Behavior extends BlockBehavior {
  BlockType<BlockElement105Behavior> ELEMENT_105_TYPE = BlockTypeBuilder
          .builder(BlockElement105Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_105)
          .build();
}
