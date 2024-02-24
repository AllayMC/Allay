package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMedicineStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMedicineStackInitializer {
  static void init() {
    ItemTypes.MEDICINE_TYPE = ItemTypeBuilder
            .builder(ItemMedicineStack.class)
            .vanillaItem(VanillaItemId.MEDICINE)
            .build();
  }
}
