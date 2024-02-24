package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement21Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement21BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_21_TYPE = BlockTypeBuilder
            .builder(BlockElement21Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_21)
            .build();
  }
}
