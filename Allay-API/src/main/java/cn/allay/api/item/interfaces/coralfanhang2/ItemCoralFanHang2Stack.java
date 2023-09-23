package cn.allay.api.item.interfaces.coralfanhang2;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHang2Stack extends ItemStack {
    ItemType<ItemCoralFanHang2Stack> CORAL_FAN_HANG2_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang2Stack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG2)
            .build();
}
