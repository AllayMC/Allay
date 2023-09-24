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
public interface BlockPortalBehavior extends BlockBehavior {
    BlockType<BlockPortalBehavior> PORTAL_TYPE = BlockTypeBuilder
            .builder(BlockPortalBehavior.class)
            .vanillaBlock(VanillaBlockId.PORTAL)
            .setProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
            .build();
}
