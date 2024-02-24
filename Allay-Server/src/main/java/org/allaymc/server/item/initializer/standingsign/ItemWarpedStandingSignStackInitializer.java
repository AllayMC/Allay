package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemWarpedStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStandingSignStackInitializer {
  static void init() {
    ItemTypes.WARPED_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWarpedStandingSignStack.class)
            .vanillaItem(VanillaItemId.WARPED_STANDING_SIGN)
            .build();
  }
}
