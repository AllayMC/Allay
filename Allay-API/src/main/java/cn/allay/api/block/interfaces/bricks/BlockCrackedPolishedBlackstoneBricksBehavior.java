package cn.allay.api.block.interfaces.bricks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrackedPolishedBlackstoneBricksBehavior extends BlockBehavior {
  BlockType<BlockCrackedPolishedBlackstoneBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
          .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
          .build();
}
