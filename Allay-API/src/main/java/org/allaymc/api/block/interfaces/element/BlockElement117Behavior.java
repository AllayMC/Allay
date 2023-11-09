package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement117Behavior extends BlockBehavior {
  BlockType<BlockElement117Behavior> ELEMENT_117_TYPE = BlockTypeBuilder
          .builder(BlockElement117Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_117)
          .build();
}
