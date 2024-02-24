package org.allaymc.server.block.initializer.element;

import org.allaymc.api.block.interfaces.element.BlockElement7Behavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockElement7BehaviorInitializer {
  static void init() {
    BlockTypes.ELEMENT_7_TYPE = BlockTypeBuilder
            .builder(BlockElement7Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_7)
            .build();
  }
}
