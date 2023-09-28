package cn.allay.api.block.interfaces.log;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedCherryLogBehavior extends BlockBehavior {
  BlockType<BlockStrippedCherryLogBehavior> STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
