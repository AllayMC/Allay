package cn.allay.api.block.interfaces.liquid;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFlowingLavaBehavior extends BlockBehavior {
  BlockType<BlockFlowingLavaBehavior> FLOWING_LAVA_TYPE = BlockTypeBuilder
          .builder(BlockFlowingLavaBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWING_LAVA)
          .setProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .build();
}
