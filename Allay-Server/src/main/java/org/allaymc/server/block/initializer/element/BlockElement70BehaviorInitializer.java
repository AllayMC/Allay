package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement70Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement70BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_70_TYPE = BlockTypeBuilder
            .builder(BlockElement70Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_70)
            .build();
  }
}
