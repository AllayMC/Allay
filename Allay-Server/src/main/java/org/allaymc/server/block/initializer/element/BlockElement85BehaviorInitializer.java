package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement85Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement85BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_85_TYPE = BlockTypeBuilder
            .builder(BlockElement85Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_85)
            .build();
  }
}
