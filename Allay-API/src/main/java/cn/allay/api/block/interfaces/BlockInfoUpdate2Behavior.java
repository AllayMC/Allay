package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockInfoUpdate2Behavior extends BlockBehavior {
  BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate2Behavior.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE2)
          .build();
}
