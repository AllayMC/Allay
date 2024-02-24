package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement20Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement20BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_20_TYPE = BlockTypeBuilder
            .builder(BlockElement20Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_20)
            .build();
  }
}
