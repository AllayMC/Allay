package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnchantedGoldenAppleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnchantedGoldenAppleStackInitializer {
  static void init() {
    ItemTypes.ENCHANTED_GOLDEN_APPLE_TYPE = ItemTypeBuilder
            .builder(ItemEnchantedGoldenAppleStack.class)
            .vanillaItem(VanillaItemId.ENCHANTED_GOLDEN_APPLE)
            .build();
  }
}
