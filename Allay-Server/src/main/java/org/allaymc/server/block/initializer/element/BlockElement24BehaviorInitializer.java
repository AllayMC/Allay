package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement24Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement24BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_24_TYPE = BlockTypeBuilder
            .builder(BlockElement24Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_24)
            .build();
  }
}
