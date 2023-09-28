package cn.allay.api.block.interfaces.carpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMossCarpetBehavior extends BlockBehavior {
  BlockType<BlockMossCarpetBehavior> MOSS_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMossCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.MOSS_CARPET)
          .build();
}
