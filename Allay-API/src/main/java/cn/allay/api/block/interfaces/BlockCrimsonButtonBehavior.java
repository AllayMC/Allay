package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

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
