package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement68Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement68BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_68_TYPE = BlockTypeBuilder
            .builder(BlockElement68Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_68)
            .build();
  }
}
