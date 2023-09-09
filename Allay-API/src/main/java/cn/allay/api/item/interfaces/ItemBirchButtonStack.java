package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchButtonStack extends ItemStack {
    ItemType<ItemBirchButtonStack> BIRCH_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemBirchButtonStack.class)
            .vanillaItem(VanillaItemId.BIRCH_BUTTON)
            .build();
}
