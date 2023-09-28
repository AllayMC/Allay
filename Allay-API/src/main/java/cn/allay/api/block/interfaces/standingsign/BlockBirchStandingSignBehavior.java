package cn.allay.api.block.interfaces.standingsign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchStandingSignBehavior extends BlockBehavior {
  BlockType<BlockBirchStandingSignBehavior> BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .build();
}
