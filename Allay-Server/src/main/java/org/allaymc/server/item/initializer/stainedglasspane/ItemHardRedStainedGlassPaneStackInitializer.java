package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardRedStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardRedStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_RED_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardRedStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_RED_STAINED_GLASS_PANE)
            .build();
  }
}
