package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemMangroveHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveHangingSignStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveHangingSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_HANGING_SIGN)
            .build();
  }
}
