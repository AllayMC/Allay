package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement52Behavior extends BlockBehavior {
  BlockType<BlockElement52Behavior> ELEMENT_52_TYPE = BlockTypeBuilder
          .builder(BlockElement52Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_52)
          .build();
}
