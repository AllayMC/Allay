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
public interface BlockCherryStandingSignBehavior extends BlockBehavior {
  BlockType<BlockCherryStandingSignBehavior> CHERRY_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .build();
}
