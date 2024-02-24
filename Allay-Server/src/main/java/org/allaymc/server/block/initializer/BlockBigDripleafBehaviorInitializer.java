package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBigDripleafBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBigDripleafBehaviorInitializer {
  static void init() {
    BlockTypes.BIG_DRIPLEAF_TYPE = BlockTypeBuilder
            .builder(BlockBigDripleafBehavior.class)
            .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF)
            .setProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
