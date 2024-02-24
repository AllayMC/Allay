package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement110Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement110BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_110_TYPE = BlockTypeBuilder
            .builder(BlockElement110Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_110)
            .build();
  }
}
