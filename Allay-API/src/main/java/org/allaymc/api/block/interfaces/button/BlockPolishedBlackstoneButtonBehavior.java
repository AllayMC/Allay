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
public interface BlockPolishedBlackstoneButtonBehavior extends BlockBehavior {
  BlockType<BlockPolishedBlackstoneButtonBehavior> POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
