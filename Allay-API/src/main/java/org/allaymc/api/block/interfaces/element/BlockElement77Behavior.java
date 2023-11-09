package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement77Behavior extends BlockBehavior {
  BlockType<BlockElement77Behavior> ELEMENT_77_TYPE = BlockTypeBuilder
          .builder(BlockElement77Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_77)
          .build();
}
