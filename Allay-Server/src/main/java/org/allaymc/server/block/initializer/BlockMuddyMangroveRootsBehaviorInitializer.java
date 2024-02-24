package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockMuddyMangroveRootsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMuddyMangroveRootsBehaviorInitializer {
  static void init() {
    BlockTypes.MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
            .builder(BlockMuddyMangroveRootsBehavior.class)
            .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
