package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakSignStack extends ItemStack {
    ItemType<ItemOakSignStack> OAK_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemOakSignStack.class)
            .vanillaItem(VanillaItemId.OAK_SIGN)
            .build();
}
