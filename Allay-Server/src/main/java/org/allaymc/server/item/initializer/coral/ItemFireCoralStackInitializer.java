package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemFireCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireCoralStackInitializer {
  static void init() {
    ItemTypes.FIRE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemFireCoralStack.class)
            .vanillaItem(VanillaItemId.FIRE_CORAL)
            .build();
  }
}
