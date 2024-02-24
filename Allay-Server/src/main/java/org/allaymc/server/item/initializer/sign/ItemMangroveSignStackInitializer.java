package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemMangroveSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveSignStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_SIGN)
            .build();
  }
}
