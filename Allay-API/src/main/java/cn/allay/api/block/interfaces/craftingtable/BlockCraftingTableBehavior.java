package cn.allay.api.block.interfaces.craftingtable;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCraftingTableBehavior extends BlockBehavior {
    BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE_TYPE = BlockTypeBuilder
            .builder(BlockCraftingTableBehavior.class)
            .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
            .build();
}
