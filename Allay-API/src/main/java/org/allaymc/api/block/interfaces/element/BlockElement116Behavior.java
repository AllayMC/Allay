package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement116Behavior extends BlockBehavior {
  BlockType<BlockElement116Behavior> ELEMENT_116_TYPE = BlockTypeBuilder
          .builder(BlockElement116Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_116)
          .build();
}
