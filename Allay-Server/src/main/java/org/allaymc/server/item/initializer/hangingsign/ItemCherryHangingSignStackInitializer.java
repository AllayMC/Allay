package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemCherryHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryHangingSignStackInitializer {
  static void init() {
    ItemTypes.CHERRY_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherryHangingSignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_HANGING_SIGN)
            .build();
  }
}
