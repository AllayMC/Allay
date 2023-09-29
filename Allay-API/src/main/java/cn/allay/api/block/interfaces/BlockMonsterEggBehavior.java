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
public interface BlockMonsterEggBehavior extends BlockBehavior {
  BlockType<BlockMonsterEggBehavior> MONSTER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockMonsterEggBehavior.class)
          .vanillaBlock(VanillaBlockId.MONSTER_EGG)
          .setProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
          .build();
}
