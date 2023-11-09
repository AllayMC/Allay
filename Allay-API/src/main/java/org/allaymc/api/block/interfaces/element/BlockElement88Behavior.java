package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement88Behavior extends BlockBehavior {
  BlockType<BlockElement88Behavior> ELEMENT_88_TYPE = BlockTypeBuilder
          .builder(BlockElement88Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_88)
          .build();
}
