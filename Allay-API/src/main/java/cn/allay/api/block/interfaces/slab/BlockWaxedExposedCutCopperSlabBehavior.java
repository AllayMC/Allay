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
public interface BlockWaxedExposedCutCopperSlabBehavior extends BlockBehavior {
  BlockType<BlockWaxedExposedCutCopperSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
