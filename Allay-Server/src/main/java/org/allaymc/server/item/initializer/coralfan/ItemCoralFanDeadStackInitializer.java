package org.allaymc.server.item.initializer.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coralfan.ItemCoralFanDeadStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanDeadStackInitializer {
  static void init() {
    ItemTypes.CORAL_FAN_DEAD_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanDeadStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN_DEAD)
            .build();
  }
}
