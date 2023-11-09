package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
