package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemCrimsonSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonSignStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonSignStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_SIGN)
            .build();
  }
}
