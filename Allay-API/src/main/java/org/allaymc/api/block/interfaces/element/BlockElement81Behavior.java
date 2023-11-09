package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement81Behavior extends BlockBehavior {
  BlockType<BlockElement81Behavior> ELEMENT_81_TYPE = BlockTypeBuilder
          .builder(BlockElement81Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_81)
          .build();
}
