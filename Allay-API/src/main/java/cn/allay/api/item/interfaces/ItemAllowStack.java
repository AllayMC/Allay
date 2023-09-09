package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAllowStack extends ItemStack {
    ItemType<ItemAllowStack> ALLOW_TYPE = ItemTypeBuilder
            .builder(ItemAllowStack.class)
            .vanillaItem(VanillaItemId.ALLOW)
            .build();
}
