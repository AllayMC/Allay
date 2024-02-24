package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement31Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement31BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_31_TYPE = BlockTypeBuilder
            .builder(BlockElement31Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_31)
            .build();
  }
}
