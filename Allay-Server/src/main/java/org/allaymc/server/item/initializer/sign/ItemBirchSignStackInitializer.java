package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemBirchSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchSignStackInitializer {
  static void init() {
    ItemTypes.BIRCH_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_SIGN)
            .build();
  }
}
