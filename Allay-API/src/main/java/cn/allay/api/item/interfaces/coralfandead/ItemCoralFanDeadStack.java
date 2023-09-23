package cn.allay.api.item.interfaces.coralfandead;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanDeadStack extends ItemStack {
    ItemType<ItemCoralFanDeadStack> CORAL_FAN_DEAD_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanDeadStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_DEAD)
            .build();
}
