package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement62Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement62BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_62_TYPE = BlockTypeBuilder
            .builder(BlockElement62Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_62)
            .build();
  }
}
