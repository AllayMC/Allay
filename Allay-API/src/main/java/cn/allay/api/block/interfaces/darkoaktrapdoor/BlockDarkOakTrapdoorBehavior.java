package cn.allay.api.block.interfaces.darkoaktrapdoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDarkOakTrapdoorBehavior extends BlockBehavior {
    BlockType<BlockDarkOakTrapdoorBehavior> DARK_OAK_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}
