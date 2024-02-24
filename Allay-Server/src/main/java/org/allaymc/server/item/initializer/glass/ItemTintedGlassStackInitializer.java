package org.allaymc.server.item.initializer.glass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.glass.ItemTintedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTintedGlassStackInitializer {
  static void init() {
    ItemTypes.TINTED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemTintedGlassStack.class)
            .vanillaItem(VanillaItemId.TINTED_GLASS)
            .build();
  }
}
