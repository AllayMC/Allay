package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement75Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement75BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_75_TYPE = BlockTypeBuilder
            .builder(BlockElement75Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_75)
            .build();
  }
}
