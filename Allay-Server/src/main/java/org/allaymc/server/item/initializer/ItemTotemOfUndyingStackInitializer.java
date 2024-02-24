package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTotemOfUndyingStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTotemOfUndyingStackInitializer {
  static void init() {
    ItemTypes.TOTEM_OF_UNDYING_TYPE = ItemTypeBuilder
            .builder(ItemTotemOfUndyingStack.class)
            .vanillaItem(VanillaItemId.TOTEM_OF_UNDYING)
            .build();
  }
}
