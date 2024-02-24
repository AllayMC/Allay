package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement5Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement5BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_5_TYPE = BlockTypeBuilder
            .builder(BlockElement5Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_5)
            .build();
  }
}
