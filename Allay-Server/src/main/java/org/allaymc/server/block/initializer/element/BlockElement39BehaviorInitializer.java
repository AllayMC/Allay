package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement39Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement39BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_39_TYPE = BlockTypeBuilder
            .builder(BlockElement39Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_39)
            .build();
  }
}
