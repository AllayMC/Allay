package org.allaymc.api.block.interfaces.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCrimsonButtonBehavior extends BlockBehavior {
  BlockType<BlockCrimsonButtonBehavior> CRIMSON_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
