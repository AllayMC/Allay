package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement82Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement82BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_82_TYPE = BlockTypeBuilder
            .builder(BlockElement82Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_82)
            .build();
  }
}
