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
public interface BlockPolishedBlackstoneSlabBehavior extends BlockBehavior {
  BlockType<BlockPolishedBlackstoneSlabBehavior> POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
