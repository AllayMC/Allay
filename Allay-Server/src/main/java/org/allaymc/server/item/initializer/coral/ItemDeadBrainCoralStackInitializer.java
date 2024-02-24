package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemDeadBrainCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadBrainCoralStackInitializer {
  static void init() {
    ItemTypes.DEAD_BRAIN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadBrainCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_BRAIN_CORAL)
            .build();
  }
}
