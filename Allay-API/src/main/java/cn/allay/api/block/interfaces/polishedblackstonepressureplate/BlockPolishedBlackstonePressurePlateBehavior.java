package cn.allay.api.block.interfaces.polishedblackstonepressureplate;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPolishedBlackstonePressurePlateBehavior extends BlockBehavior {
    BlockType<BlockPolishedBlackstonePressurePlateBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
            .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
            .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
            .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
            .build();
}
