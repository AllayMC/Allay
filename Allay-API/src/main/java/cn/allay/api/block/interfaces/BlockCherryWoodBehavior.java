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
public interface BlockCherryWoodBehavior extends BlockBehavior {
    BlockType<BlockCherryWoodBehavior> CHERRY_WOOD_TYPE = BlockTypeBuilder
            .builder(BlockCherryWoodBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_WOOD)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
            .build();
}
