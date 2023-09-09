package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoatHornStack extends ItemStack {
    ItemType<ItemGoatHornStack> GOAT_HORN_TYPE = ItemTypeBuilder
            .builder(ItemGoatHornStack.class)
            .vanillaItem(VanillaItemId.GOAT_HORN)
            .build();
}
