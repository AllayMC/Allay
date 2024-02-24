package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement41Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement41BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_41_TYPE = BlockTypeBuilder
            .builder(BlockElement41Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_41)
            .build();
  }
}
