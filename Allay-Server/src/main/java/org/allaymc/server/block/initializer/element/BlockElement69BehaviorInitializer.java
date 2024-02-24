package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement69Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement69BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_69_TYPE = BlockTypeBuilder
            .builder(BlockElement69Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_69)
            .build();
  }
}
