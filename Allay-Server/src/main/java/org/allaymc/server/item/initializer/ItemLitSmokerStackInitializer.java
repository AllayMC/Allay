package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLitSmokerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitSmokerStackInitializer {
  static void init() {
    ItemTypes.LIT_SMOKER_TYPE = ItemTypeBuilder
            .builder(ItemLitSmokerStack.class)
            .vanillaItem(VanillaItemId.LIT_SMOKER)
            .build();
  }
}
