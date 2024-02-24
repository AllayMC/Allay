package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardLimeStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardLimeStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_LIME_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardLimeStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_LIME_STAINED_GLASS_PANE)
            .build();
  }
}
