package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement62Behavior extends BlockBehavior {
  BlockType<BlockElement62Behavior> ELEMENT_62_TYPE = BlockTypeBuilder
          .builder(BlockElement62Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_62)
          .build();
}
