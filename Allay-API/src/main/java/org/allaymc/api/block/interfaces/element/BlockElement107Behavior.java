package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement107Behavior extends BlockBehavior {
  BlockType<BlockElement107Behavior> ELEMENT_107_TYPE = BlockTypeBuilder
          .builder(BlockElement107Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_107)
          .build();
}
