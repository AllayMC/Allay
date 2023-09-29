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
public interface BlockLanternBehavior extends BlockBehavior {
  BlockType<BlockLanternBehavior> LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.LANTERN)
          .setProperties(VanillaBlockPropertyTypes.HANGING)
          .build();
}
