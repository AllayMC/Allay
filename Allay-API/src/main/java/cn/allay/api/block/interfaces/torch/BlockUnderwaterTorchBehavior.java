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
public interface BlockUnderwaterTorchBehavior extends BlockBehavior {
  BlockType<BlockUnderwaterTorchBehavior> UNDERWATER_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnderwaterTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
          .build();
}
