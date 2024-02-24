package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemDeadFireCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadFireCoralStackInitializer {
  static void init() {
    ItemTypes.DEAD_FIRE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadFireCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_FIRE_CORAL)
            .build();
  }
}
