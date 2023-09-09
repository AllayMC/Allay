package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHang3Stack extends ItemStack {
    ItemType<ItemCoralFanHang3Stack> CORAL_FAN_HANG3_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang3Stack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG3)
            .build();
}
