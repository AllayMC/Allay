package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement42Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement42BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_42_TYPE = BlockTypeBuilder
            .builder(BlockElement42Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_42)
            .build();
  }
}
