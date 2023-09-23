package cn.allay.api.item.interfaces.cake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCakeStack extends ItemStack {
    ItemType<ItemCakeStack> CAKE_TYPE = ItemTypeBuilder
            .builder(ItemCakeStack.class)
            .vanillaItem(VanillaItemId.CAKE)
            .build();
}
