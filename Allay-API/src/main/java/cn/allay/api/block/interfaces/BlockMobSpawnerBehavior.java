package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMobSpawnerBehavior extends BlockBehavior {
  BlockType<BlockMobSpawnerBehavior> MOB_SPAWNER_TYPE = BlockTypeBuilder
          .builder(BlockMobSpawnerBehavior.class)
          .vanillaBlock(VanillaBlockId.MOB_SPAWNER)
          .build();
}
