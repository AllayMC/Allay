package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement114Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement114BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_114_TYPE = BlockTypeBuilder
            .builder(BlockElement114Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_114)
            .build();
  }
}
