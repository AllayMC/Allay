package cn.allay.api.block.interfaces.carpet;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOrangeCarpetBehavior extends BlockBehavior {
  BlockType<BlockOrangeCarpetBehavior> ORANGE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCarpetBehavior.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CARPET)
          .build();
}
