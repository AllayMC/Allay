package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBoneBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBoneBlockBehaviorInitializer {
  static void init() {
    BlockTypes.BONE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockBoneBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.BONE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
  }
}
