package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement116Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement116BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_116_TYPE = BlockTypeBuilder
            .builder(BlockElement116Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_116)
            .build();
  }
}
