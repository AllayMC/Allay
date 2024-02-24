package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement83Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement83BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_83_TYPE = BlockTypeBuilder
            .builder(BlockElement83Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_83)
            .build();
  }
}
