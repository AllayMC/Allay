package org.allaymc.api.block.interfaces.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTorchflowerCropBehavior extends BlockBehavior {
  BlockType<BlockTorchflowerCropBehavior> TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerCropBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .build();
}
