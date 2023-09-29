package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSnowBehavior extends BlockBehavior {
  BlockType<BlockSnowBehavior> SNOW_TYPE = BlockTypeBuilder
          .builder(BlockSnowBehavior.class)
          .vanillaBlock(VanillaBlockId.SNOW)
          .build();
}
