package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement49Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement49BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_49_TYPE = BlockTypeBuilder
            .builder(BlockElement49Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_49)
            .build();
  }
}
