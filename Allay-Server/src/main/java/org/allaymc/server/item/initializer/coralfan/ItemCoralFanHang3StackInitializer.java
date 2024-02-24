package org.allaymc.server.item.initializer.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coralfan.ItemCoralFanHang3Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHang3StackInitializer {
  static void init() {
    ItemTypes.CORAL_FAN_HANG3_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang3Stack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG3)
            .build();
  }
}
