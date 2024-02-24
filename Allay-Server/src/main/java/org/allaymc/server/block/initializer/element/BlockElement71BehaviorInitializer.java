package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement71Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement71BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_71_TYPE = BlockTypeBuilder
            .builder(BlockElement71Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_71)
            .build();
  }
}
