package cn.allay.api.block.interfaces.cherrytrapdoor;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCherryTrapdoorBehavior extends BlockBehavior {
    BlockType<BlockCherryTrapdoorBehavior> CHERRY_TRAPDOOR_TYPE = BlockTypeBuilder
            .builder(BlockCherryTrapdoorBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT)
            .build();
}
