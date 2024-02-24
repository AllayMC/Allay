package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement2Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement2BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_2_TYPE = BlockTypeBuilder
            .builder(BlockElement2Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_2)
            .build();
  }
}
