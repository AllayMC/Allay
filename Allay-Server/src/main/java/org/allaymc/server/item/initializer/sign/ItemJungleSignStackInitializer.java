package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemJungleSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleSignStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_SIGN)
            .build();
  }
}
