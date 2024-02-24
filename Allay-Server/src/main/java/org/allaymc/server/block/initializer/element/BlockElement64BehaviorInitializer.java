package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement64Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement64BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_64_TYPE = BlockTypeBuilder
            .builder(BlockElement64Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_64)
            .build();
  }
}
