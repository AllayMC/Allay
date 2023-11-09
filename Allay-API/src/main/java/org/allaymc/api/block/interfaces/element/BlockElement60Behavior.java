package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement60Behavior extends BlockBehavior {
  BlockType<BlockElement60Behavior> ELEMENT_60_TYPE = BlockTypeBuilder
          .builder(BlockElement60Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_60)
          .build();
}
