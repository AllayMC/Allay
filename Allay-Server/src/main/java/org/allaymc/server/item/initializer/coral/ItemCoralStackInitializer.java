package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralStackInitializer {
  static void init() {
    ItemTypes.CORAL_TYPE = ItemTypeBuilder
            .builder(ItemCoralStack.class)
            .vanillaItem(VanillaItemId.CORAL)
            .build();
  }
}
