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
public interface BlockActivatorRailBehavior extends BlockBehavior {
    BlockType<BlockActivatorRailBehavior> ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
            .builder(BlockActivatorRailBehavior.class)
            .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL)
            .setProperties(VanillaBlockPropertyTypes.RAIL_DATA_BIT, VanillaBlockPropertyTypes.RAIL_DIRECTION_6)
            .build();
}
