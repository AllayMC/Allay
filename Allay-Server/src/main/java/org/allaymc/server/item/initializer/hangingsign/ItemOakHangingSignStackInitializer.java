package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemOakHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakHangingSignStackInitializer {
  static void init() {
    ItemTypes.OAK_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemOakHangingSignStack.class)
            .vanillaItem(VanillaItemId.OAK_HANGING_SIGN)
            .build();
  }
}
