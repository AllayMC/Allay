package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement38Behavior extends BlockBehavior {
  BlockType<BlockElement38Behavior> ELEMENT_38_TYPE = BlockTypeBuilder
          .builder(BlockElement38Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_38)
          .build();
}
