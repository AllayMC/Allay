package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFermentedSpiderEyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFermentedSpiderEyeStackInitializer {
  static void init() {
    ItemTypes.FERMENTED_SPIDER_EYE_TYPE = ItemTypeBuilder
            .builder(ItemFermentedSpiderEyeStack.class)
            .vanillaItem(VanillaItemId.FERMENTED_SPIDER_EYE)
            .build();
  }
}
