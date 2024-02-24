package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement88Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement88BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_88_TYPE = BlockTypeBuilder
            .builder(BlockElement88Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_88)
            .build();
  }
}
