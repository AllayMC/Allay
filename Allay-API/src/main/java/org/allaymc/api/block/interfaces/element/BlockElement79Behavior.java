package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement79Behavior extends BlockBehavior {
  BlockType<BlockElement79Behavior> ELEMENT_79_TYPE = BlockTypeBuilder
          .builder(BlockElement79Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_79)
          .build();
}
