package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakHangingSignStack extends ItemStack {
    ItemType<ItemOakHangingSignStack> OAK_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemOakHangingSignStack.class)
            .vanillaItem(VanillaItemId.OAK_HANGING_SIGN)
            .build();
}
