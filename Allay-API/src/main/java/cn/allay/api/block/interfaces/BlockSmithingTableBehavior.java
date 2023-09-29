package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSmithingTableBehavior extends BlockBehavior {
  BlockType<BlockSmithingTableBehavior> SMITHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockSmithingTableBehavior.class)
          .vanillaBlock(VanillaBlockId.SMITHING_TABLE)
          .build();
}
