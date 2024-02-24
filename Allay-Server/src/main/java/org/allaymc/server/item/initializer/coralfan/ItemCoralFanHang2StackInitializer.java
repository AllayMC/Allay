package org.allaymc.server.item.initializer.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coralfan.ItemCoralFanHang2Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHang2StackInitializer {
  static void init() {
    ItemTypes.CORAL_FAN_HANG2_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHang2Stack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG2)
            .build();
  }
}
