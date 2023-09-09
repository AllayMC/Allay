package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakHangingSignStack extends ItemStack {
    ItemType<ItemDarkOakHangingSignStack> DARK_OAK_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakHangingSignStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_HANGING_SIGN)
            .build();
}
