package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement105Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement105BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_105_TYPE = BlockTypeBuilder
            .builder(BlockElement105Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_105)
            .build();
  }
}
