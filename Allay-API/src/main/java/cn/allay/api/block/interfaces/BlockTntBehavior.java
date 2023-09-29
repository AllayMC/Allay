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
public interface BlockTntBehavior extends BlockBehavior {
  BlockType<BlockTntBehavior> TNT_TYPE = BlockTypeBuilder
          .builder(BlockTntBehavior.class)
          .vanillaBlock(VanillaBlockId.TNT)
          .setProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
          .build();
}
