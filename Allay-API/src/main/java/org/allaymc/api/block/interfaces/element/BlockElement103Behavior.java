package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement103Behavior extends BlockBehavior {
  BlockType<BlockElement103Behavior> ELEMENT_103_TYPE = BlockTypeBuilder
          .builder(BlockElement103Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_103)
          .build();
}
