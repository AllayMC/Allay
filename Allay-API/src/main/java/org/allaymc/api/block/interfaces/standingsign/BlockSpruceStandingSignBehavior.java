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
public interface BlockSpruceStandingSignBehavior extends BlockBehavior {
  BlockType<BlockSpruceStandingSignBehavior> SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStandingSignBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .build();
}
