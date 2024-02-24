package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement73Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement73BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_73_TYPE = BlockTypeBuilder
            .builder(BlockElement73Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_73)
            .build();
  }
}
