package cn.allay.api.block.interfaces.brownmushroom;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrownMushroomBehavior extends BlockBehavior {
    BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM_TYPE = BlockTypeBuilder
            .builder(BlockBrownMushroomBehavior.class)
            .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM)
            .build();
}
