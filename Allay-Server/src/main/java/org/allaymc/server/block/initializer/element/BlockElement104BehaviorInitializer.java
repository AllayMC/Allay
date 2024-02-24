package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement104Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement104BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_104_TYPE = BlockTypeBuilder
            .builder(BlockElement104Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_104)
            .build();
  }
}
