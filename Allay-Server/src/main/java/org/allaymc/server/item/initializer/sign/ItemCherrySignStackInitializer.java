package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemCherrySignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySignStackInitializer {
  static void init() {
    ItemTypes.CHERRY_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherrySignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_SIGN)
            .build();
  }
}
