package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement60Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement60BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_60_TYPE = BlockTypeBuilder
            .builder(BlockElement60Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_60)
            .build();
  }
}
