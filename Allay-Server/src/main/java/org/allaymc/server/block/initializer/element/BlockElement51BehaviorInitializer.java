package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement51Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement51BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_51_TYPE = BlockTypeBuilder
            .builder(BlockElement51Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_51)
            .build();
  }
}
