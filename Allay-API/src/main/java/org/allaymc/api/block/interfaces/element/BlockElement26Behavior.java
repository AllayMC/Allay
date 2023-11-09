package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement26Behavior extends BlockBehavior {
  BlockType<BlockElement26Behavior> ELEMENT_26_TYPE = BlockTypeBuilder
          .builder(BlockElement26Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_26)
          .build();
}
