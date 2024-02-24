package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement43Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement43BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_43_TYPE = BlockTypeBuilder
            .builder(BlockElement43Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_43)
            .build();
  }
}
