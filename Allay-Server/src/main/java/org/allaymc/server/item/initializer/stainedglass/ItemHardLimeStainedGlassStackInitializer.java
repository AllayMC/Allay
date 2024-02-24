package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardLimeStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardLimeStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_LIME_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardLimeStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_LIME_STAINED_GLASS)
            .build();
  }
}
