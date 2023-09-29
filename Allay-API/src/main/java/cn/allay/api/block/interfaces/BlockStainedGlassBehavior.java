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
public interface BlockStainedGlassBehavior extends BlockBehavior {
  BlockType<BlockStainedGlassBehavior> STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassBehavior.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS)
          .setProperties(VanillaBlockPropertyTypes.COLOR)
          .build();
}
