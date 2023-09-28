package cn.allay.api.block.interfaces.concrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowConcreteBehavior extends BlockBehavior {
  BlockType<BlockYellowConcreteBehavior> YELLOW_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockYellowConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CONCRETE)
          .build();
}
