package cn.allay.api.block.interfaces.suspicioussand;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSuspiciousSandBehavior extends BlockBehavior {
    BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
            .builder(BlockSuspiciousSandBehavior.class)
            .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND)
            .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
            .build();
}
