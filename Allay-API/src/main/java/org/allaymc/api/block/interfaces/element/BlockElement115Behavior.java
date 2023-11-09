package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement115Behavior extends BlockBehavior {
  BlockType<BlockElement115Behavior> ELEMENT_115_TYPE = BlockTypeBuilder
          .builder(BlockElement115Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_115)
          .build();
}
