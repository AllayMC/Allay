package cn.allay.api.block.interfaces.farmland;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFarmlandBehavior extends BlockBehavior {
    BlockType<BlockFarmlandBehavior> FARMLAND_TYPE = BlockTypeBuilder
            .builder(BlockFarmlandBehavior.class)
            .vanillaBlock(VanillaBlockId.FARMLAND)
            .setProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
            .build();
}
