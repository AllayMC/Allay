package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChemistryTableStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChemistryTableStackInitializer {
  static void init() {
    ItemTypes.CHEMISTRY_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemChemistryTableStack.class)
            .vanillaItem(VanillaItemId.CHEMISTRY_TABLE)
            .build();
  }
}
