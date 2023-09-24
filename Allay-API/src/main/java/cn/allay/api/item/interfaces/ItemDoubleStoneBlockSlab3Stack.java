package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleStoneBlockSlab3Stack extends ItemStack {
    ItemType<ItemDoubleStoneBlockSlab3Stack> DOUBLE_STONE_BLOCK_SLAB3_TYPE = ItemTypeBuilder
            .builder(ItemDoubleStoneBlockSlab3Stack.class)
            .vanillaItem(VanillaItemId.DOUBLE_STONE_BLOCK_SLAB3)
            .build();
}
