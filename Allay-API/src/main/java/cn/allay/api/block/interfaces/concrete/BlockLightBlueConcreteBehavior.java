package cn.allay.api.block.interfaces.concrete;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueConcreteBehavior extends BlockBehavior {
  BlockType<BlockLightBlueConcreteBehavior> LIGHT_BLUE_CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueConcreteBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CONCRETE)
          .build();
}
