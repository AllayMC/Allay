package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemBambooSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSignStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBambooSignStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_SIGN)
            .build();
  }
}
