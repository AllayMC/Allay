package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement11Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement11BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_11_TYPE = BlockTypeBuilder
            .builder(BlockElement11Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_11)
            .build();
  }
}
