package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement91Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement91BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_91_TYPE = BlockTypeBuilder
            .builder(BlockElement91Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_91)
            .build();
  }
}
