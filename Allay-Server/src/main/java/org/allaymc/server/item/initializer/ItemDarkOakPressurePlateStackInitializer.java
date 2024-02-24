package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDarkOakPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakPressurePlateStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakPressurePlateStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_PRESSURE_PLATE)
            .build();
  }
}
