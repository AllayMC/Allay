package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement113Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement113BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_113_TYPE = BlockTypeBuilder
            .builder(BlockElement113Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_113)
            .build();
  }
}
