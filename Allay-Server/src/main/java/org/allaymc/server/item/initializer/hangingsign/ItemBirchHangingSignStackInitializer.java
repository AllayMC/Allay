package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemBirchHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchHangingSignStackInitializer {
  static void init() {
    ItemTypes.BIRCH_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchHangingSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_HANGING_SIGN)
            .build();
  }
}
