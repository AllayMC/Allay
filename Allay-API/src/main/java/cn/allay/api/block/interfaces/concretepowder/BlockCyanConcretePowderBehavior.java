package cn.allay.api.block.interfaces.concretepowder;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCyanConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockCyanConcretePowderBehavior> CYAN_CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockCyanConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.CYAN_CONCRETE_POWDER)
          .build();
}
