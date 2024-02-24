package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardBlueStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBlueStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_BLUE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardBlueStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_BLUE_STAINED_GLASS_PANE)
            .build();
  }
}
