package cn.allay.api.block.interfaces.oaklog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOakLogBehavior extends BlockBehavior {
    BlockType<BlockOakLogBehavior> OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
