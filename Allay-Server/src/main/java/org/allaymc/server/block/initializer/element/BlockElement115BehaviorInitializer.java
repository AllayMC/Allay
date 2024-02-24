package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement115Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement115BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_115_TYPE = BlockTypeBuilder
            .builder(BlockElement115Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_115)
            .build();
  }
}
