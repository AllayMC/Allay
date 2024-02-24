package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPearlescentFroglightStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPearlescentFroglightStackInitializer {
  static void init() {
    ItemTypes.PEARLESCENT_FROGLIGHT_TYPE = ItemTypeBuilder
            .builder(ItemPearlescentFroglightStack.class)
            .vanillaItem(VanillaItemId.PEARLESCENT_FROGLIGHT)
            .build();
  }
}
