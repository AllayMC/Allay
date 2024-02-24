package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemSpruceStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceStandingSignStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceStandingSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_STANDING_SIGN)
            .build();
  }
}
