package org.allaymc.server.item.initializer.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coralfan.ItemCoralFanHangStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHangStackInitializer {
  static void init() {
    ItemTypes.CORAL_FAN_HANG_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanHangStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_HANG)
            .build();
  }
}
