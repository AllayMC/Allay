package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPotionStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotionStackInitializer {
  static void init() {
    ItemTypes.POTION_TYPE = ItemTypeBuilder
            .builder(ItemPotionStack.class)
            .vanillaItem(VanillaItemId.POTION)
            .build();
  }
}
