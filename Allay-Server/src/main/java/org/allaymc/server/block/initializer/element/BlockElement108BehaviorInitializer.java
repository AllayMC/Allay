package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement108Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement108BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_108_TYPE = BlockTypeBuilder
            .builder(BlockElement108Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_108)
            .build();
  }
}
