package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement28Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement28BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_28_TYPE = BlockTypeBuilder
            .builder(BlockElement28Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_28)
            .build();
  }
}
