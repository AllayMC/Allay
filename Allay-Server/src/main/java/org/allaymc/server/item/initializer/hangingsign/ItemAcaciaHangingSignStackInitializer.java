package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemAcaciaHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaHangingSignStackInitializer {
  static void init() {
    ItemTypes.ACACIA_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaHangingSignStack.class)
            .vanillaItem(VanillaItemId.ACACIA_HANGING_SIGN)
            .build();
  }
}
