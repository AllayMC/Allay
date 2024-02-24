package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement80Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement80BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_80_TYPE = BlockTypeBuilder
            .builder(BlockElement80Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_80)
            .build();
  }
}
