package cn.allay.api.block.interfaces.slab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWeatheredDoubleCutCopperSlabBehavior extends BlockBehavior {
  BlockType<BlockWeatheredDoubleCutCopperSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
