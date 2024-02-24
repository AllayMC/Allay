package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement45Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement45BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_45_TYPE = BlockTypeBuilder
            .builder(BlockElement45Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_45)
            .build();
  }
}
