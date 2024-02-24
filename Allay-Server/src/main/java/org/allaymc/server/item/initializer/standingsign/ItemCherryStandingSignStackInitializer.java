package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemCherryStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryStandingSignStackInitializer {
  static void init() {
    ItemTypes.CHERRY_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCherryStandingSignStack.class)
            .vanillaItem(VanillaItemId.CHERRY_STANDING_SIGN)
            .build();
  }
}
