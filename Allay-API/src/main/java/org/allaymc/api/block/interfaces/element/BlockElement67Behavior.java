package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement67Behavior extends BlockBehavior {
  BlockType<BlockElement67Behavior> ELEMENT_67_TYPE = BlockTypeBuilder
          .builder(BlockElement67Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_67)
          .build();
}
