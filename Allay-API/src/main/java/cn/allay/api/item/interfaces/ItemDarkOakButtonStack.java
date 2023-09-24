package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakButtonStack extends ItemStack {
    ItemType<ItemDarkOakButtonStack> DARK_OAK_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakButtonStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_BUTTON)
            .build();
}
