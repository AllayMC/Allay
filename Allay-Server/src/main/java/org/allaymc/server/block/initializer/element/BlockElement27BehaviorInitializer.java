package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement27Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement27BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_27_TYPE = BlockTypeBuilder
            .builder(BlockElement27Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_27)
            .build();
  }
}
