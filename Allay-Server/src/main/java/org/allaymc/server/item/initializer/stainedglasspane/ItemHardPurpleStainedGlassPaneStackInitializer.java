package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardPurpleStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardPurpleStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_PURPLE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardPurpleStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_PURPLE_STAINED_GLASS_PANE)
            .build();
  }
}
