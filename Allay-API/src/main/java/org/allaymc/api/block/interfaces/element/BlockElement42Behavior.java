package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement42Behavior extends BlockBehavior {
  BlockType<BlockElement42Behavior> ELEMENT_42_TYPE = BlockTypeBuilder
          .builder(BlockElement42Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_42)
          .build();
}
