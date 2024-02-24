package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.STAINED_GLASS_PANE)
            .build();
  }
}
