package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemDeadHornCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadHornCoralStackInitializer {
  static void init() {
    ItemTypes.DEAD_HORN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadHornCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_HORN_CORAL)
            .build();
  }
}
