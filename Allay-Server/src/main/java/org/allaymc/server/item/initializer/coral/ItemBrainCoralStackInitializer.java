package org.allaymc.server.item.initializer.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.coral.ItemBrainCoralStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrainCoralStackInitializer {
  static void init() {
    ItemTypes.BRAIN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemBrainCoralStack.class)
            .vanillaItem(VanillaItemId.BRAIN_CORAL)
            .build();
  }
}
