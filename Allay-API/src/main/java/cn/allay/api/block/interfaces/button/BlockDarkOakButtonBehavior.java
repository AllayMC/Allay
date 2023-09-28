package cn.allay.api.block.interfaces.button;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakButtonBehavior extends BlockBehavior {
  BlockType<BlockDarkOakButtonBehavior> DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
