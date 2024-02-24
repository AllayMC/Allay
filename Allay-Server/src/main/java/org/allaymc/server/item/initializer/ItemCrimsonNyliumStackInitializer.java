package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCrimsonNyliumStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonNyliumStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_NYLIUM_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonNyliumStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_NYLIUM)
            .build();
  }
}
