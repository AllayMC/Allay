package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement76Behavior extends BlockBehavior {
  BlockType<BlockElement76Behavior> ELEMENT_76_TYPE = BlockTypeBuilder
          .builder(BlockElement76Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_76)
          .build();
}
