package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement38Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement38BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_38_TYPE = BlockTypeBuilder
            .builder(BlockElement38Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_38)
            .build();
  }
}
