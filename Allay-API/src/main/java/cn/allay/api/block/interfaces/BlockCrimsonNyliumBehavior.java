package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonNyliumBehavior extends BlockBehavior {
  BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonNyliumBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM)
          .build();
}
