package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement72Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement72BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_72_TYPE = BlockTypeBuilder
            .builder(BlockElement72Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_72)
            .build();
  }
}
