package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChemicalHeatStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemicalHeatStackInitializer {
  static void init() {
    ItemTypes.CHEMICAL_HEAT_TYPE = ItemTypeBuilder
            .builder(ItemChemicalHeatStack.class)
            .vanillaItem(VanillaItemId.CHEMICAL_HEAT)
            .build();
  }
}
