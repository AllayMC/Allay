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
public interface BlockColoredTorchBpBehavior extends BlockBehavior {
  BlockType<BlockColoredTorchBpBehavior> COLORED_TORCH_BP_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchBpBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .build();
}
