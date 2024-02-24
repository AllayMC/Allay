package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemOakSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakSignStackInitializer {
  static void init() {
    ItemTypes.OAK_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemOakSignStack.class)
            .vanillaItem(VanillaItemId.OAK_SIGN)
            .build();
  }
}
