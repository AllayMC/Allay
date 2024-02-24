package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement23Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement23BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_23_TYPE = BlockTypeBuilder
            .builder(BlockElement23Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_23)
            .build();
  }
}
