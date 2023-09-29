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
public interface BlockSculkCatalystBehavior extends BlockBehavior {
  BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST_TYPE = BlockTypeBuilder
          .builder(BlockSculkCatalystBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
          .setProperties(VanillaBlockPropertyTypes.BLOOM)
          .build();
}
