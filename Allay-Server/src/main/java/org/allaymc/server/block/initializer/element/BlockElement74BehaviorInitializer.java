package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement74Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement74BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_74_TYPE = BlockTypeBuilder
            .builder(BlockElement74Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_74)
            .build();
  }
}
