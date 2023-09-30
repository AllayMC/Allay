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
public interface BlockDoubleStoneBlockSlab3Behavior extends BlockBehavior {
  BlockType<BlockDoubleStoneBlockSlab3Behavior> DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab3Behavior.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3)
          .setProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
