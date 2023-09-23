package cn.allay.api.block.interfaces.dragonegg;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDragonEggBehavior extends BlockBehavior {
    BlockType<BlockDragonEggBehavior> DRAGON_EGG_TYPE = BlockTypeBuilder
            .builder(BlockDragonEggBehavior.class)
            .vanillaBlock(VanillaBlockId.DRAGON_EGG)
            .build();
}
