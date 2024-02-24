package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemLightGrayStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
