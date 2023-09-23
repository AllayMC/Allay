package cn.allay.api.item.interfaces.craftingtable;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCraftingTableStack extends ItemStack {
    ItemType<ItemCraftingTableStack> CRAFTING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemCraftingTableStack.class)
            .vanillaItem(VanillaItemId.CRAFTING_TABLE)
            .build();
}
