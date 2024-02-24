package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLingeringPotionStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLingeringPotionStackInitializer {
  static void init() {
    ItemTypes.LINGERING_POTION_TYPE = ItemTypeBuilder
            .builder(ItemLingeringPotionStack.class)
            .vanillaItem(VanillaItemId.LINGERING_POTION)
            .build();
  }
}
