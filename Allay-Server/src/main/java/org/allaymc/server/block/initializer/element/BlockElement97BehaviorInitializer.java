package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement97Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement97BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_97_TYPE = BlockTypeBuilder
            .builder(BlockElement97Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_97)
            .build();
  }
}
