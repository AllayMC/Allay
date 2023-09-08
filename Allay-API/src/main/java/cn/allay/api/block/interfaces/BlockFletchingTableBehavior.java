package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockFletchingTableBehavior extends BlockBehavior {
    BlockType<BlockFletchingTableBehavior> FLETCHING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockFletchingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.FLETCHING_TABLE)
            .build();
}
