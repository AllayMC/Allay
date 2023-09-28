package cn.allay.api.block.interfaces.wool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWhiteWoolBehavior extends BlockBehavior {
  BlockType<BlockWhiteWoolBehavior> WHITE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockWhiteWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.WHITE_WOOL)
          .build();
}
