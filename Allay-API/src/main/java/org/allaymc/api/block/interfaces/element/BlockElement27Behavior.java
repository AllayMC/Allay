package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement27Behavior extends BlockBehavior {
  BlockType<BlockElement27Behavior> ELEMENT_27_TYPE = BlockTypeBuilder
          .builder(BlockElement27Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_27)
          .build();
}
