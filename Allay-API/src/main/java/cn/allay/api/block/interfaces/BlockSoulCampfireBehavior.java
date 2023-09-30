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
public interface BlockSoulCampfireBehavior extends BlockBehavior {
  BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .build();
}
