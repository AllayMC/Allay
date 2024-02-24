package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement18Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement18BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_18_TYPE = BlockTypeBuilder
            .builder(BlockElement18Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_18)
            .build();
  }
}
