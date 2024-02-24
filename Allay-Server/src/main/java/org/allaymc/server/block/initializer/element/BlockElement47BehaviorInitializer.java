package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement47Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement47BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_47_TYPE = BlockTypeBuilder
            .builder(BlockElement47Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_47)
            .build();
  }
}
