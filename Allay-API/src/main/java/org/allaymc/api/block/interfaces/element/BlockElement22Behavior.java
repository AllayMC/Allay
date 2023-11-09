package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement22Behavior extends BlockBehavior {
  BlockType<BlockElement22Behavior> ELEMENT_22_TYPE = BlockTypeBuilder
          .builder(BlockElement22Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_22)
          .build();
}
