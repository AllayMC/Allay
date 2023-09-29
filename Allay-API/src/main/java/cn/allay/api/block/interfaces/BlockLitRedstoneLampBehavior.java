package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitRedstoneLampBehavior extends BlockBehavior {
  BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneLampBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP)
          .build();
}
