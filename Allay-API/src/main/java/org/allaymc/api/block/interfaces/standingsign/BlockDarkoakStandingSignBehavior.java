package org.allaymc.api.block.interfaces.standingsign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkoakStandingSignBehavior extends BlockBehavior {
  BlockType<BlockDarkoakStandingSignBehavior> DARKOAK_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .build();
}
