package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemJungleStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleStandingSignStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleStandingSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_STANDING_SIGN)
            .build();
  }
}
