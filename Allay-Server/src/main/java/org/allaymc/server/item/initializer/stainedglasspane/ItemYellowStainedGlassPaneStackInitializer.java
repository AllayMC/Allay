package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemYellowStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.YELLOW_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemYellowStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.YELLOW_STAINED_GLASS_PANE)
            .build();
  }
}
