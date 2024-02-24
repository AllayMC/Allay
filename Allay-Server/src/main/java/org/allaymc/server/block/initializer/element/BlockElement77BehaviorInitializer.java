package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement77Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement77BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_77_TYPE = BlockTypeBuilder
            .builder(BlockElement77Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_77)
            .build();
  }
}
