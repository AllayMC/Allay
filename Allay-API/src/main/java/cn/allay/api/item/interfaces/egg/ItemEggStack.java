package cn.allay.api.item.interfaces.egg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEggStack extends ItemStack {
    ItemType<ItemEggStack> EGG_TYPE = ItemTypeBuilder
            .builder(ItemEggStack.class)
            .vanillaItem(VanillaItemId.EGG)
            .build();
}
