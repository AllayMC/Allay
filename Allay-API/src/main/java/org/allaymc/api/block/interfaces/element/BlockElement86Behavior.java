package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement86Behavior extends BlockBehavior {
  BlockType<BlockElement86Behavior> ELEMENT_86_TYPE = BlockTypeBuilder
          .builder(BlockElement86Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_86)
          .build();
}
