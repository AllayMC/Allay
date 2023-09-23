package cn.allay.api.block.interfaces.mangrovefencegate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveFenceGateBehavior extends BlockBehavior {
    BlockType<BlockMangroveFenceGateBehavior> MANGROVE_FENCE_GATE_TYPE = BlockTypeBuilder
            .builder(BlockMangroveFenceGateBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_FENCE_GATE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT)
            .build();
}
