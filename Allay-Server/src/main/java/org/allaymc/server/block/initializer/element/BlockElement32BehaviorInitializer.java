package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement32Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement32BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_32_TYPE = BlockTypeBuilder
            .builder(BlockElement32Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_32)
            .build();
  }
}
