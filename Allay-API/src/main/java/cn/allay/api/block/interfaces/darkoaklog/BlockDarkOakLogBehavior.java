package cn.allay.api.block.interfaces.darkoaklog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakLogBehavior extends BlockBehavior {
    BlockType<BlockDarkOakLogBehavior> DARK_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
