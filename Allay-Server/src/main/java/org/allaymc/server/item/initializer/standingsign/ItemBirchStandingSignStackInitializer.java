package org.allaymc.server.item.initializer.standingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.standingsign.ItemBirchStandingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchStandingSignStackInitializer {
  static void init() {
    ItemTypes.BIRCH_STANDING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchStandingSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_STANDING_SIGN)
            .build();
  }
}
