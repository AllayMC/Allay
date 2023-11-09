package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement97Behavior extends BlockBehavior {
  BlockType<BlockElement97Behavior> ELEMENT_97_TYPE = BlockTypeBuilder
          .builder(BlockElement97Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_97)
          .build();
}
