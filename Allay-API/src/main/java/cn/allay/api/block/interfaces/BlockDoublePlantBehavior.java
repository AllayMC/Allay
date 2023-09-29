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
public interface BlockDoublePlantBehavior extends BlockBehavior {
  BlockType<BlockDoublePlantBehavior> DOUBLE_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockDoublePlantBehavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT)
          .setProperties(VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
