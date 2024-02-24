package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement87Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement87BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_87_TYPE = BlockTypeBuilder
            .builder(BlockElement87Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_87)
            .build();
  }
}
