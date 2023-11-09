package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement31Behavior extends BlockBehavior {
  BlockType<BlockElement31Behavior> ELEMENT_31_TYPE = BlockTypeBuilder
          .builder(BlockElement31Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_31)
          .build();
}
