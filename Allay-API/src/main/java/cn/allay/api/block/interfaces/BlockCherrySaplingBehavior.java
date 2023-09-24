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
public interface BlockCherrySaplingBehavior extends BlockBehavior {
    BlockType<BlockCherrySaplingBehavior> CHERRY_SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockCherrySaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT)
            .build();
}
