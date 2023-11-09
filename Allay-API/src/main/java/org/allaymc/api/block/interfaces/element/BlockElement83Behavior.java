package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement83Behavior extends BlockBehavior {
  BlockType<BlockElement83Behavior> ELEMENT_83_TYPE = BlockTypeBuilder
          .builder(BlockElement83Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_83)
          .build();
}
