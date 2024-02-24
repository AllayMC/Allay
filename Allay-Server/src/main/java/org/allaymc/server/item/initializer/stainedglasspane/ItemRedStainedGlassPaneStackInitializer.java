package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemRedStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.RED_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemRedStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.RED_STAINED_GLASS_PANE)
            .build();
  }
}
