package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement36Behavior extends BlockBehavior {
  BlockType<BlockElement36Behavior> ELEMENT_36_TYPE = BlockTypeBuilder
          .builder(BlockElement36Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_36)
          .build();
}
