package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCalibratedSculkSensorStack extends ItemStack {
    ItemType<ItemCalibratedSculkSensorStack> CALIBRATED_SCULK_SENSOR_TYPE = ItemTypeBuilder
            .builder(ItemCalibratedSculkSensorStack.class)
            .vanillaItem(VanillaItemId.CALIBRATED_SCULK_SENSOR)
            .build();
}
