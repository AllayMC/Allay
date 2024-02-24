package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemLightBlueStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_STAINED_GLASS_PANE)
            .build();
  }
}
