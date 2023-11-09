package org.allaymc.api.block.interfaces.hangingsign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakHangingSignBehavior extends BlockBehavior {
  BlockType<BlockDarkOakHangingSignBehavior> DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakHangingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
          .build();
}
