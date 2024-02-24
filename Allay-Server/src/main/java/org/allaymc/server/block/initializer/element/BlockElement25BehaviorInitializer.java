package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement25Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement25BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_25_TYPE = BlockTypeBuilder
            .builder(BlockElement25Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_25)
            .build();
  }
}
