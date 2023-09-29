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
public interface BlockVerdantFroglightBehavior extends BlockBehavior {
  BlockType<BlockVerdantFroglightBehavior> VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockVerdantFroglightBehavior.class)
          .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
