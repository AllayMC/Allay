package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBubbleColumnBehavior extends BlockBehavior {
  BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN_TYPE = BlockTypeBuilder
          .builder(BlockBubbleColumnBehavior.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN)
          .setProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
          .build();
}
