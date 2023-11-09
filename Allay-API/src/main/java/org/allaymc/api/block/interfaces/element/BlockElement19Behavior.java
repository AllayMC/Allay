package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement19Behavior extends BlockBehavior {
  BlockType<BlockElement19Behavior> ELEMENT_19_TYPE = BlockTypeBuilder
          .builder(BlockElement19Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_19)
          .build();
}
