package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement70Behavior extends BlockBehavior {
  BlockType<BlockElement70Behavior> ELEMENT_70_TYPE = BlockTypeBuilder
          .builder(BlockElement70Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_70)
          .build();
}
