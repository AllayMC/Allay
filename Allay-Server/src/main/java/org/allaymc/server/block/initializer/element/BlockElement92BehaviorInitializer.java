package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement92Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement92BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_92_TYPE = BlockTypeBuilder
            .builder(BlockElement92Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_92)
            .build();
  }
}