package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBoneBlockBehavior extends BlockBehavior {
  BlockType<BlockBoneBlockBehavior> BONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBoneBlockBehavior.class)
          .vanillaBlock(VanillaBlockId.BONE_BLOCK)
          .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
