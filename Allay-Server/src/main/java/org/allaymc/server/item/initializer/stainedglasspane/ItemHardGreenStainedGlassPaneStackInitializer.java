package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardGreenStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGreenStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_GREEN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardGreenStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_GREEN_STAINED_GLASS_PANE)
            .build();
  }
}
