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
public interface BlockCherryLeavesBehavior extends BlockBehavior {
  BlockType<BlockCherryLeavesBehavior> CHERRY_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockCherryLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .build();
}
