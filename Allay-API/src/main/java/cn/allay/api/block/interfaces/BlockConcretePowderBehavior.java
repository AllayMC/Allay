package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockConcretePowderBehavior extends BlockBehavior {
  BlockType<BlockConcretePowderBehavior> CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockConcretePowderBehavior.class)
          .vanillaBlock(VanillaBlockId.CONCRETE_POWDER)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .build();
}
