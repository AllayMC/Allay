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
public interface BlockEndPortalFrameBehavior extends BlockBehavior {
    BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockEndPortalFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT)
            .build();
}
