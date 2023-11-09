package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement65Behavior extends BlockBehavior {
  BlockType<BlockElement65Behavior> ELEMENT_65_TYPE = BlockTypeBuilder
          .builder(BlockElement65Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_65)
          .build();
}
