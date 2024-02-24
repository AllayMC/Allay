package org.allaymc.server.item.initializer.sign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sign.ItemSpruceSignStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceSignStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemSpruceSignStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_SIGN)
            .build();
  }
}
