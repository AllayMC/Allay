package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement33Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement33BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_33_TYPE = BlockTypeBuilder
            .builder(BlockElement33Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_33)
            .build();
  }
}
