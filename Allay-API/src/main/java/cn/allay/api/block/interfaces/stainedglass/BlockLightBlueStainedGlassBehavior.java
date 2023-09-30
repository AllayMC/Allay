package cn.allay.api.block.interfaces.stainedglass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightBlueStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockLightBlueStainedGlassBehavior> LIGHT_BLUE_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_STAINED_GLASS)
          .build();
}
