package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardYellowStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardYellowStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_YELLOW_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardYellowStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_YELLOW_STAINED_GLASS_PANE)
            .build();
  }
}
