package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement50Behavior extends BlockBehavior {
  BlockType<BlockElement50Behavior> ELEMENT_50_TYPE = BlockTypeBuilder
          .builder(BlockElement50Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_50)
          .build();
}
