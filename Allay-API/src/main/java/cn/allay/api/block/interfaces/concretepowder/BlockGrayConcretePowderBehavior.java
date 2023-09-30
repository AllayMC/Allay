package cn.allay.api.block.interfaces.concretepowder;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGrayConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockGrayConcretePowderBehavior> GRAY_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockGrayConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.GRAY_CONCRETE_POWDER)
          .build();
}
