package cn.allay.api.block.interfaces.wool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanWoolBehavior extends BlockBehavior {
  BlockType<BlockCyanWoolBehavior> CYAN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockCyanWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_WOOL)
          .build();
}
