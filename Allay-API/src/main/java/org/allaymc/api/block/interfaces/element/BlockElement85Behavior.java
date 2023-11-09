package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement85Behavior extends BlockBehavior {
  BlockType<BlockElement85Behavior> ELEMENT_85_TYPE = BlockTypeBuilder
          .builder(BlockElement85Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_85)
          .build();
}
