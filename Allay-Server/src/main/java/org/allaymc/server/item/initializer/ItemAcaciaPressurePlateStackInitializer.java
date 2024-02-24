package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAcaciaPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaPressurePlateStackInitializer {
  static void init() {
    ItemTypes.ACACIA_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaPressurePlateStack.class)
            .vanillaItem(VanillaItemId.ACACIA_PRESSURE_PLATE)
            .build();
  }
}
