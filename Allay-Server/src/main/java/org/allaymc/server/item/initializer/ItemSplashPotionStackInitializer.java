package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSplashPotionStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSplashPotionStackInitializer {
  static void init() {
    ItemTypes.SPLASH_POTION_TYPE = ItemTypeBuilder
            .builder(ItemSplashPotionStack.class)
            .vanillaItem(VanillaItemId.SPLASH_POTION)
            .build();
  }
}
