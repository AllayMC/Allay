package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement99Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement99BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_99_TYPE = BlockTypeBuilder
            .builder(BlockElement99Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_99)
            .build();
  }
}
