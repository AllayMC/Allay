package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemCrimsonStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStandingSignStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonStandingSignStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_STANDING_SIGN)
            .build();
  }
}
