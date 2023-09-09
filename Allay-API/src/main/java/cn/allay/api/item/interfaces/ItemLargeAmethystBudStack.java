package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLargeAmethystBudStack extends ItemStack {
    ItemType<ItemLargeAmethystBudStack> LARGE_AMETHYST_BUD_TYPE = ItemTypeBuilder
            .builder(ItemLargeAmethystBudStack.class)
            .vanillaItem(VanillaItemId.LARGE_AMETHYST_BUD)
            .build();
}
