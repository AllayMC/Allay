package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement59Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement59BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_59_TYPE = BlockTypeBuilder
            .builder(BlockElement59Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_59)
            .build();
  }
}
