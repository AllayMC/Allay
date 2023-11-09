package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement98Behavior extends BlockBehavior {
  BlockType<BlockElement98Behavior> ELEMENT_98_TYPE = BlockTypeBuilder
          .builder(BlockElement98Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_98)
          .build();
}
