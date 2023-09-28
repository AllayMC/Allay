package cn.allay.api.block.interfaces.hangingsign;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveHangingSignBehavior extends BlockBehavior {
  BlockType<BlockMangroveHangingSignBehavior> MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .build();
}
