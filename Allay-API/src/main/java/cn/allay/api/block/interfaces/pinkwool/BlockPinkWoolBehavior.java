package cn.allay.api.block.interfaces.pinkwool;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPinkWoolBehavior extends BlockBehavior {
    BlockType<BlockPinkWoolBehavior> PINK_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockPinkWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_WOOL)
            .build();
}
