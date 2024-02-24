package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement63Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement63BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_63_TYPE = BlockTypeBuilder
            .builder(BlockElement63Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_63)
            .build();
  }
}
