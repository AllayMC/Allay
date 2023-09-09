package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantingTableStack extends ItemStack {
    ItemType<ItemEnchantingTableStack> ENCHANTING_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemEnchantingTableStack.class)
            .vanillaItem(VanillaItemId.ENCHANTING_TABLE)
            .build();
}
