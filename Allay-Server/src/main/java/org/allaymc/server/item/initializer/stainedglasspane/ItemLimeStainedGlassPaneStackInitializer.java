package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemLimeStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.LIME_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemLimeStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.LIME_STAINED_GLASS_PANE)
            .build();
  }
}
