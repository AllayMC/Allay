package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement46Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement46BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_46_TYPE = BlockTypeBuilder
            .builder(BlockElement46Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_46)
            .build();
  }
}
