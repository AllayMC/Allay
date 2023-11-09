package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement99Behavior extends BlockBehavior {
  BlockType<BlockElement99Behavior> ELEMENT_99_TYPE = BlockTypeBuilder
          .builder(BlockElement99Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_99)
          .build();
}
