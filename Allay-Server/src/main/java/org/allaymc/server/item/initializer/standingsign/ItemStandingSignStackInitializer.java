package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStandingSignStackInitializer {
  static void init() {
    ItemTypes.STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemStandingSignStack.class)
            .vanillaItem(VanillaItemId.STANDING_SIGN)
            .build();
  }
}
