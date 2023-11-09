package org.allaymc.api.block.interfaces.element;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement7Behavior extends BlockBehavior {
  BlockType<BlockElement7Behavior> ELEMENT_7_TYPE = BlockTypeBuilder
          .builder(BlockElement7Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_7)
          .build();
}
