package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement107Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement107BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_107_TYPE = BlockTypeBuilder
            .builder(BlockElement107Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_107)
            .build();
  }
}
