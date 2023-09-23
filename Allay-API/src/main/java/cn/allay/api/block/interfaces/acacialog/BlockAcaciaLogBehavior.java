package cn.allay.api.block.interfaces.acacialog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAcaciaLogBehavior extends BlockBehavior {
    BlockType<BlockAcaciaLogBehavior> ACACIA_LOG_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaLogBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
