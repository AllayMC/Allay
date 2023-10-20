package cn.allay.api.block.interfaces.torch;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
