package org.allaymc.server.item.initializer.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coralfan.ItemCoralFanStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanStackInitializer {
  static void init() {
    ItemTypes.CORAL_FAN_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN)
            .build();
  }
}
