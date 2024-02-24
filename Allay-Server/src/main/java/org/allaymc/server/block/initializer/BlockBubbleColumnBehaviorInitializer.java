package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBubbleColumnBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBubbleColumnBehaviorInitializer {
  static void init() {
    BlockTypes.BUBBLE_COLUMN_TYPE = BlockTypeBuilder
            .builder(BlockBubbleColumnBehavior.class)
            .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN)
            .setProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
            .build();
  }
}
