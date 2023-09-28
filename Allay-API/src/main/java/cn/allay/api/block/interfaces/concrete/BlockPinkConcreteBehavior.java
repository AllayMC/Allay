package cn.allay.api.block.interfaces.concrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkConcreteBehavior extends BlockBehavior {
  BlockType<BlockPinkConcreteBehavior> PINK_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockPinkConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CONCRETE)
          .build();
}
