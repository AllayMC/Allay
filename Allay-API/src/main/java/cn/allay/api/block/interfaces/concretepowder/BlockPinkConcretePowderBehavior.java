package cn.allay.api.block.interfaces.concretepowder;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockPinkConcretePowderBehavior> PINK_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockPinkConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.PINK_CONCRETE_POWDER)
          .build();
}
