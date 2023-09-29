package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSoulSandBehavior extends BlockBehavior {
  BlockType<BlockSoulSandBehavior> SOUL_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSoulSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_SAND)
          .build();
}
