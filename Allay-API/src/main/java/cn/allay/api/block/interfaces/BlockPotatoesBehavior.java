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
public interface BlockPotatoesBehavior extends BlockBehavior {
  BlockType<BlockPotatoesBehavior> POTATOES_TYPE = BlockTypeBuilder
          .builder(BlockPotatoesBehavior.class)
          .vanillaBlock(VanillaBlockId.POTATOES)
          .setProperties(VanillaBlockPropertyTypes.GROWTH)
          .build();
}
