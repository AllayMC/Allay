package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement26Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement26BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_26_TYPE = BlockTypeBuilder
            .builder(BlockElement26Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_26)
            .build();
  }
}
