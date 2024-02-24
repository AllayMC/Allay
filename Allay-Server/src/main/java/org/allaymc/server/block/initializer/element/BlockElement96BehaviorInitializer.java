package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement96Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement96BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_96_TYPE = BlockTypeBuilder
            .builder(BlockElement96Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_96)
            .build();
  }
}
