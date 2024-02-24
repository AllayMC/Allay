package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement8Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement8BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_8_TYPE = BlockTypeBuilder
            .builder(BlockElement8Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_8)
            .build();
  }
}
