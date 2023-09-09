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
public interface BlockFlowerPotBehavior extends BlockBehavior {
    BlockType<BlockFlowerPotBehavior> FLOWER_POT_TYPE = BlockTypeBuilder
            .builder(BlockFlowerPotBehavior.class)
            .vanillaBlock(VanillaBlockId.FLOWER_POT)
            .setProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
            .build();
}
