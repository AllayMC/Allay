package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkSensorStack extends ItemStack {
    ItemType<ItemSculkSensorStack> SCULK_SENSOR_TYPE = ItemTypeBuilder
            .builder(ItemSculkSensorStack.class)
            .vanillaItem(VanillaItemId.SCULK_SENSOR)
            .build();
}
