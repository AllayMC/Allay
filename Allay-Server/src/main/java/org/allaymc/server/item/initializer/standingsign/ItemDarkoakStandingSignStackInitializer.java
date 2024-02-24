package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemDarkoakStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakStandingSignStackInitializer {
  static void init() {
    ItemTypes.DARKOAK_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemDarkoakStandingSignStack.class)
            .vanillaItem(VanillaItemId.DARKOAK_STANDING_SIGN)
            .build();
  }
}
