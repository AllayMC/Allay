package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement29Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement29BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_29_TYPE = BlockTypeBuilder
            .builder(BlockElement29Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_29)
            .build();
  }
}
