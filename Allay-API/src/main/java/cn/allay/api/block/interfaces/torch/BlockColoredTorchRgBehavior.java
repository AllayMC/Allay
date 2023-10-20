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
public interface BlockColoredTorchRgBehavior extends BlockBehavior {
  BlockType<BlockColoredTorchRgBehavior> COLORED_TORCH_RG_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchRgBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
          .build();
}
