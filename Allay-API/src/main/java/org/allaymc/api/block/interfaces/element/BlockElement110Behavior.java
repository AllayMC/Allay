package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement110Behavior extends BlockBehavior {
  BlockType<BlockElement110Behavior> ELEMENT_110_TYPE = BlockTypeBuilder
          .builder(BlockElement110Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_110)
          .build();
}
