package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement98Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement98BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_98_TYPE = BlockTypeBuilder
            .builder(BlockElement98Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_98)
            .build();
  }
}
