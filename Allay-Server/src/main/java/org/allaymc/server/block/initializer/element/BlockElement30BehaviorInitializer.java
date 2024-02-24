package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement30Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement30BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_30_TYPE = BlockTypeBuilder
            .builder(BlockElement30Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_30)
            .build();
  }
}
