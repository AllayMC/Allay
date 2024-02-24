package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement22Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement22BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_22_TYPE = BlockTypeBuilder
            .builder(BlockElement22Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_22)
            .build();
  }
}
