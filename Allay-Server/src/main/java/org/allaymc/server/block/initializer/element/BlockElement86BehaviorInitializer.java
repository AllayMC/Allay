package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement86Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement86BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_86_TYPE = BlockTypeBuilder
            .builder(BlockElement86Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_86)
            .build();
  }
}
