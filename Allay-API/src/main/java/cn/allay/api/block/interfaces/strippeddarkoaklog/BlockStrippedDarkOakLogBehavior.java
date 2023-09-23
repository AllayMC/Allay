package cn.allay.api.block.interfaces.strippeddarkoaklog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedDarkOakLogBehavior extends BlockBehavior {
    BlockType<BlockStrippedDarkOakLogBehavior> STRIPPED_DARK_OAK_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedDarkOakLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
