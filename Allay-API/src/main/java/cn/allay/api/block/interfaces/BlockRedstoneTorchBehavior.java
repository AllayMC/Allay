package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedstoneTorchBehavior extends BlockBehavior {
  BlockType<BlockRedstoneTorchBehavior> REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .build();
}
