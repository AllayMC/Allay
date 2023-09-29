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
public interface BlockSculkVeinBehavior extends BlockBehavior {
  BlockType<BlockSculkVeinBehavior> SCULK_VEIN_TYPE = BlockTypeBuilder
          .builder(BlockSculkVeinBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_VEIN)
          .setProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .build();
}
