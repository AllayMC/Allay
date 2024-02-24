package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement48Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement48BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_48_TYPE = BlockTypeBuilder
            .builder(BlockElement48Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_48)
            .build();
  }
}
