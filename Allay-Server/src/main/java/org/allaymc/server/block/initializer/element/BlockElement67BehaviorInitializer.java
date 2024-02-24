package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement67Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement67BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_67_TYPE = BlockTypeBuilder
            .builder(BlockElement67Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_67)
            .build();
  }
}
