package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement89Behavior extends BlockBehavior {
  BlockType<BlockElement89Behavior> ELEMENT_89_TYPE = BlockTypeBuilder
          .builder(BlockElement89Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_89)
          .build();
}
