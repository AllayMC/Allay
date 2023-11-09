package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement80Behavior extends BlockBehavior {
  BlockType<BlockElement80Behavior> ELEMENT_80_TYPE = BlockTypeBuilder
          .builder(BlockElement80Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_80)
          .build();
}
