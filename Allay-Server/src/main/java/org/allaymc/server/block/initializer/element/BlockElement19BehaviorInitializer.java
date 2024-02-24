package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement19Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement19BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_19_TYPE = BlockTypeBuilder
            .builder(BlockElement19Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_19)
            .build();
  }
}
