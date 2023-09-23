package cn.allay.api.item.interfaces.enchantedbook;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantedBookStack extends ItemStack {
    ItemType<ItemEnchantedBookStack> ENCHANTED_BOOK_TYPE = ItemTypeBuilder
            .builder(ItemEnchantedBookStack.class)
            .vanillaItem(VanillaItemId.ENCHANTED_BOOK)
            .build();
}
