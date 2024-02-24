package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemWarpedHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedHangingSignStackInitializer {
  static void init() {
    ItemTypes.WARPED_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWarpedHangingSignStack.class)
            .vanillaItem(VanillaItemId.WARPED_HANGING_SIGN)
            .build();
  }
}
