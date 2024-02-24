package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemAcaciaSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaSignStackInitializer {
  static void init() {
    ItemTypes.ACACIA_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaSignStack.class)
            .vanillaItem(VanillaItemId.ACACIA_SIGN)
            .build();
  }
}
