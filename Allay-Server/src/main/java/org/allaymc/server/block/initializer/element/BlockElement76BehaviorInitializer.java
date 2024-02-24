package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement76Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement76BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_76_TYPE = BlockTypeBuilder
            .builder(BlockElement76Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_76)
            .build();
  }
}
