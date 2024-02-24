package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemLimeStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeStainedGlassStackInitializer {
  static void init() {
    ItemTypes.LIME_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemLimeStainedGlassStack.class)
            .vanillaItem(VanillaItemId.LIME_STAINED_GLASS)
            .build();
  }
}
