package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement14Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement14BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_14_TYPE = BlockTypeBuilder
            .builder(BlockElement14Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_14)
            .build();
  }
}
