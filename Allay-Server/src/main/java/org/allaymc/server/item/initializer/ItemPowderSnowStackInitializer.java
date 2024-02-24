package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPowderSnowStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPowderSnowStackInitializer {
  static void init() {
    ItemTypes.POWDER_SNOW_TYPE = ItemTypeBuilder
            .builder(ItemPowderSnowStack.class)
            .vanillaItem(VanillaItemId.POWDER_SNOW)
            .build();
  }
}
