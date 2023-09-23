package cn.allay.api.item.interfaces.sugarcane;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSugarCaneStack extends ItemStack {
    ItemType<ItemSugarCaneStack> SUGAR_CANE_TYPE = ItemTypeBuilder
            .builder(ItemSugarCaneStack.class)
            .vanillaItem(VanillaItemId.SUGAR_CANE)
            .build();
}
