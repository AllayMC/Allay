package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemReinforcedDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemReinforcedDeepslateStackInitializer {
  static void init() {
    ItemTypes.REINFORCED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemReinforcedDeepslateStack.class)
            .vanillaItem(VanillaItemId.REINFORCED_DEEPSLATE)
            .build();
  }
}
