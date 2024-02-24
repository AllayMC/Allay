package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement101Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement101BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_101_TYPE = BlockTypeBuilder
            .builder(BlockElement101Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_101)
            .build();
  }
}
