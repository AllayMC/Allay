package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShieldStackInitializer {
  static void init() {
    ItemTypes.SHIELD_TYPE = ItemTypeBuilder
            .builder(ItemShieldStack.class)
            .vanillaItem(VanillaItemId.SHIELD)
            .build();
  }
}
