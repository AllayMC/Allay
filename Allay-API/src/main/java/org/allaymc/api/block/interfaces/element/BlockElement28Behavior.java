package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement28Behavior extends BlockBehavior {
  BlockType<BlockElement28Behavior> ELEMENT_28_TYPE = BlockTypeBuilder
          .builder(BlockElement28Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_28)
          .build();
}
