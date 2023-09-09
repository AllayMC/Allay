package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab4Stack extends ItemStack {
    ItemType<ItemDoubleStoneBlockSlab4Stack> DOUBLE_STONE_BLOCK_SLAB4_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab4Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB4)
            .build();
}
