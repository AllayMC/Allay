package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement6Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement6BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_6_TYPE = BlockTypeBuilder
            .builder(BlockElement6Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_6)
            .build();
  }
}
