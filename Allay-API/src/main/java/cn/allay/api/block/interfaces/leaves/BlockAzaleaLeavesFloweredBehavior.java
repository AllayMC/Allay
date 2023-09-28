package cn.allay.api.block.interfaces.leaves;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAzaleaLeavesFloweredBehavior extends BlockBehavior {
  BlockType<BlockAzaleaLeavesFloweredBehavior> AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesFloweredBehavior.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .build();
}
