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
public interface BlockComposterBehavior extends BlockBehavior {
  BlockType<BlockComposterBehavior> COMPOSTER_TYPE = BlockTypeBuilder
          .builder(BlockComposterBehavior.class)
          .vanillaBlock(VanillaBlockId.COMPOSTER)
          .setProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
          .build();
}
