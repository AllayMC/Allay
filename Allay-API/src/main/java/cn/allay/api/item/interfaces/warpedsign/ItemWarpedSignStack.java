package cn.allay.api.item.interfaces.warpedsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedSignStack extends ItemStack {
    ItemType<ItemWarpedSignStack> WARPED_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWarpedSignStack.class)
            .vanillaItem(VanillaItemId.WARPED_SIGN)
            .build();
}
