package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement100Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement100BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_100_TYPE = BlockTypeBuilder
            .builder(BlockElement100Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_100)
            .build();
  }
}
