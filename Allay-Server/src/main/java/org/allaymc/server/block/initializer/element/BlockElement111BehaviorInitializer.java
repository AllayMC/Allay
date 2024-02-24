package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement111Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement111BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_111_TYPE = BlockTypeBuilder
            .builder(BlockElement111Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_111)
            .build();
  }
}
