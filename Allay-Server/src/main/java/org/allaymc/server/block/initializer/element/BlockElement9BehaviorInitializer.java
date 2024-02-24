package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement9Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement9BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_9_TYPE = BlockTypeBuilder
            .builder(BlockElement9Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_9)
            .build();
  }
}
