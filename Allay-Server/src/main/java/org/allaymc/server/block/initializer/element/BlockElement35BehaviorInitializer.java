package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement35Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement35BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_35_TYPE = BlockTypeBuilder
            .builder(BlockElement35Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_35)
            .build();
  }
}
