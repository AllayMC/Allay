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
public interface BlockGlowFrameBehavior extends BlockBehavior {
    BlockType<BlockGlowFrameBehavior> GLOW_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockGlowFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.GLOW_FRAME)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
            .build();
}
