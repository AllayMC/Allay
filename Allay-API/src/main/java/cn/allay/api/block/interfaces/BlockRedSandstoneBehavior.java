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
public interface BlockRedSandstoneBehavior extends BlockBehavior {
  BlockType<BlockRedSandstoneBehavior> RED_SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE)
          .setProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .build();
}
