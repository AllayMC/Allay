package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemDarkOakHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakHangingSignStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakHangingSignStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_HANGING_SIGN)
            .build();
  }
}
