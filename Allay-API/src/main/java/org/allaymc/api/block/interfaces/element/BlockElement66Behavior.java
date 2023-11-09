package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement66Behavior extends BlockBehavior {
  BlockType<BlockElement66Behavior> ELEMENT_66_TYPE = BlockTypeBuilder
          .builder(BlockElement66Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_66)
          .build();
}
