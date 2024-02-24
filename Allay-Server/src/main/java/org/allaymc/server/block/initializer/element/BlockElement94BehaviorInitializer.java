package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement94Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement94BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_94_TYPE = BlockTypeBuilder
            .builder(BlockElement94Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_94)
            .build();
  }
}
