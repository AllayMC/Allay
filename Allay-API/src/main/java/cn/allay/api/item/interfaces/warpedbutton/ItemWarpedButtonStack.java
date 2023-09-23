package cn.allay.api.item.interfaces.warpedbutton;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedButtonStack extends ItemStack {
    ItemType<ItemWarpedButtonStack> WARPED_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemWarpedButtonStack.class)
            .vanillaItem(VanillaItemId.WARPED_BUTTON)
            .build();
}
