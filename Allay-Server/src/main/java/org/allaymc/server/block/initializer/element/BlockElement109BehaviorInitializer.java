package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement109Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement109BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_109_TYPE = BlockTypeBuilder
            .builder(BlockElement109Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_109)
            .build();
  }
}
