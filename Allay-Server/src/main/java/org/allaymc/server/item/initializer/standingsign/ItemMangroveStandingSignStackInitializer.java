package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemMangroveStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveStandingSignStackInitializer {
  static void init() {
    ItemTypes.MANGROVE_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveStandingSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_STANDING_SIGN)
            .build();
  }
}
