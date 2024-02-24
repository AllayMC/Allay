package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement95Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement95BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_95_TYPE = BlockTypeBuilder
            .builder(BlockElement95Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_95)
            .build();
  }
}
