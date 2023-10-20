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
public interface BlockSoulTorchBehavior extends BlockBehavior {
  BlockType<BlockSoulTorchBehavior> SOUL_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockSoulTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
          .build();
}
