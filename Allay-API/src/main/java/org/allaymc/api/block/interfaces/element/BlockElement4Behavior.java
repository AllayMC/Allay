package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement4Behavior extends BlockBehavior {
  BlockType<BlockElement4Behavior> ELEMENT_4_TYPE = BlockTypeBuilder
          .builder(BlockElement4Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_4)
          .build();
}
