package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemBambooStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooStandingSignStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBambooStandingSignStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_STANDING_SIGN)
            .build();
  }
}
