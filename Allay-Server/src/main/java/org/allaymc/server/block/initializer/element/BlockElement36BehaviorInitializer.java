package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement36Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement36BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_36_TYPE = BlockTypeBuilder
            .builder(BlockElement36Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_36)
            .build();
  }
}
