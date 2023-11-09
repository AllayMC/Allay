package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement92Behavior extends BlockBehavior {
  BlockType<BlockElement92Behavior> ELEMENT_92_TYPE = BlockTypeBuilder
          .builder(BlockElement92Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_92)
          .build();
}
