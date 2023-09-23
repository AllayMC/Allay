package cn.allay.api.block.interfaces.polishedblackstonebutton;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
