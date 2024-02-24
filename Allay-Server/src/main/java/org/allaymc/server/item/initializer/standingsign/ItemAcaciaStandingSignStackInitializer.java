package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemAcaciaStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaStandingSignStackInitializer {
  static void init() {
    ItemTypes.ACACIA_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaStandingSignStack.class)
            .vanillaItem(VanillaItemId.ACACIA_STANDING_SIGN)
            .build();
  }
}
