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
public interface BlockPitcherCropBehavior extends BlockBehavior {
  BlockType<BlockPitcherCropBehavior> PITCHER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockPitcherCropBehavior.class)
          .vanillaBlock(VanillaBlockId.PITCHER_CROP)
          .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
