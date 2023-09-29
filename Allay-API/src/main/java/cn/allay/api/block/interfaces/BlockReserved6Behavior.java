package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockReserved6Behavior extends BlockBehavior {
  BlockType<BlockReserved6Behavior> RESERVED6_TYPE = BlockTypeBuilder
          .builder(BlockReserved6Behavior.class)
          .vanillaBlock(VanillaBlockId.RESERVED6)
          .build();
}
