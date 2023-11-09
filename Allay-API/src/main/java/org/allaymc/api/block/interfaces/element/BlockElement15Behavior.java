package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement15Behavior extends BlockBehavior {
  BlockType<BlockElement15Behavior> ELEMENT_15_TYPE = BlockTypeBuilder
          .builder(BlockElement15Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_15)
          .build();
}
