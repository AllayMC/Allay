package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement55Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement55BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_55_TYPE = BlockTypeBuilder
            .builder(BlockElement55Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_55)
            .build();
  }
}
