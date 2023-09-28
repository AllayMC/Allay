package cn.allay.api.block.interfaces.shulkerbox;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlueShulkerBoxBehavior extends BlockBehavior {
  BlockType<BlockBlueShulkerBoxBehavior> BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockBlueShulkerBoxBehavior.class)
          .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
          .build();
}
