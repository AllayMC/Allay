package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJukeboxBehavior extends BlockBehavior {
  BlockType<BlockJukeboxBehavior> JUKEBOX_TYPE = BlockTypeBuilder
          .builder(BlockJukeboxBehavior.class)
          .vanillaBlock(VanillaBlockId.JUKEBOX)
          .build();
}
