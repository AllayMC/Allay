package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemAcaciaPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaPlanksStackInitializer {
  static void init() {
    ItemTypes.ACACIA_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaPlanksStack.class)
            .vanillaItem(VanillaItemId.ACACIA_PLANKS)
            .build();
  }
}
