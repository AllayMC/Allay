package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemWarpedSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedSignStackInitializer {
  static void init() {
    ItemTypes.WARPED_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemWarpedSignStack.class)
            .vanillaItem(VanillaItemId.WARPED_SIGN)
            .build();
  }
}
