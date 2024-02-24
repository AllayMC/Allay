package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemDarkOakSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakSignStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakSignStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_SIGN)
            .build();
  }
}
