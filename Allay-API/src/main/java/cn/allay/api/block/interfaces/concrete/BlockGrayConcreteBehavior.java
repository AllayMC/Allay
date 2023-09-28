package cn.allay.api.block.interfaces.concrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayConcreteBehavior extends BlockBehavior {
  BlockType<BlockGrayConcreteBehavior> GRAY_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockGrayConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CONCRETE)
          .build();
}
