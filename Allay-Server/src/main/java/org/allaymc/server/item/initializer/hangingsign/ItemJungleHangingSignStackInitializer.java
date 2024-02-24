package org.allaymc.server.item.initializer.hangingsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.hangingsign.ItemJungleHangingSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleHangingSignStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_HANGING_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleHangingSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_HANGING_SIGN)
            .build();
  }
}
