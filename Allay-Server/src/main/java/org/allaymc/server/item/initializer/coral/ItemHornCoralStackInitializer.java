package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemHornCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHornCoralStackInitializer {
  static void init() {
    ItemTypes.HORN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemHornCoralStack.class)
            .vanillaItem(VanillaItemId.HORN_CORAL)
            .build();
  }
}
