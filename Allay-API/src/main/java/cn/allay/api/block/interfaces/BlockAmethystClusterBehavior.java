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
public interface BlockAmethystClusterBehavior extends BlockBehavior {
  BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
          .builder(BlockAmethystClusterBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
          .build();
}
