package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedDeepslateBehavior extends BlockBehavior {
  BlockType<BlockPolishedDeepslateBehavior> POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE)
          .build();
}
