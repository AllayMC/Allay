package cn.allay.api.block.interfaces.shulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightGrayShulkerBoxBehavior extends BlockBehavior {
  BlockType<BlockLightGrayShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
          .build();
}
