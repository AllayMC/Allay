package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement100Behavior extends BlockBehavior {
  BlockType<BlockElement100Behavior> ELEMENT_100_TYPE = BlockTypeBuilder
          .builder(BlockElement100Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_100)
          .build();
}
