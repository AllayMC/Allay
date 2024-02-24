package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement102Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement102BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_102_TYPE = BlockTypeBuilder
            .builder(BlockElement102Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_102)
            .build();
  }
}
