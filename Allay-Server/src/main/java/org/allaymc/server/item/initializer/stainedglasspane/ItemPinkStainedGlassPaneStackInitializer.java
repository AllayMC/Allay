package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemPinkStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.PINK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemPinkStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.PINK_STAINED_GLASS_PANE)
            .build();
  }
}
