package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
