package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement109Behavior extends BlockBehavior {
  BlockType<BlockElement109Behavior> ELEMENT_109_TYPE = BlockTypeBuilder
          .builder(BlockElement109Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_109)
          .build();
}
