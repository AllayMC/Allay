package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanStack extends ItemStack {
    ItemType<ItemCoralFanStack> CORAL_FAN_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN)
            .build();
}
