package cn.allay.api.block.interfaces.acaciafencegate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockAcaciaFenceGateBehavior extends BlockBehavior {
    BlockType<BlockAcaciaFenceGateBehavior> ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
}
