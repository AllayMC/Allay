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
public interface BlockSpruceButtonBehavior extends BlockBehavior {
  BlockType<BlockSpruceButtonBehavior> SPRUCE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockSpruceButtonBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON)
          .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
