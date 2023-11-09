package org.allaymc.api.item.interfaces.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
