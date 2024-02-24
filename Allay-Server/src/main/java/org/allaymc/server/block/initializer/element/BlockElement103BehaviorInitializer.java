package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement103Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement103BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_103_TYPE = BlockTypeBuilder
            .builder(BlockElement103Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_103)
            .build();
  }
}
