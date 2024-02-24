package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement40Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement40BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_40_TYPE = BlockTypeBuilder
            .builder(BlockElement40Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_40)
            .build();
  }
}
