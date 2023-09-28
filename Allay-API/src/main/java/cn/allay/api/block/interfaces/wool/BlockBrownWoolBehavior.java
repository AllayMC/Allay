package cn.allay.api.block.interfaces.wool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownWoolBehavior extends BlockBehavior {
  BlockType<BlockBrownWoolBehavior> BROWN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBrownWoolBehavior.class)
          .vanillaBlock(VanillaBlockId.BROWN_WOOL)
          .build();
}
