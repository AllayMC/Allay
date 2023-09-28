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
public interface BlockStrippedAcaciaLogBehavior extends BlockBehavior {
  BlockType<BlockStrippedAcaciaLogBehavior> STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedAcaciaLogBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
