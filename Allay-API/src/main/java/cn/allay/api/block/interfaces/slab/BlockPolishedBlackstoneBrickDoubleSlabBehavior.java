package cn.allay.api.block.interfaces.slab;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstoneBrickDoubleSlabBehavior extends BlockBehavior {
  BlockType<BlockPolishedBlackstoneBrickDoubleSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .build();
}
