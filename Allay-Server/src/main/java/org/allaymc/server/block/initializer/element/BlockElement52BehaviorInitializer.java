package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement52Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement52BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_52_TYPE = BlockTypeBuilder
            .builder(BlockElement52Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_52)
            .build();
  }
}
