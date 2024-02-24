package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLodestoneCompassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLodestoneCompassStackInitializer {
  static void init() {
    ItemTypes.LODESTONE_COMPASS_TYPE = ItemTypeBuilder
            .builder(ItemLodestoneCompassStack.class)
            .vanillaItem(VanillaItemId.LODESTONE_COMPASS)
            .build();
  }
}
