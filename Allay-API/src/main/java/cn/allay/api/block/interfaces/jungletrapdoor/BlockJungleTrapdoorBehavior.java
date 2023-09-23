package cn.allay.api.block.interfaces.jungletrapdoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJungleTrapdoorBehavior extends BlockBehavior {
    BlockType<BlockJungleTrapdoorBehavior> JUNGLE_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockJungleTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.JUNGLE_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}
