package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPitcherPlantStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherPlantStackInitializer {
  static void init() {
    ItemTypes.PITCHER_PLANT_TYPE = ItemTypeBuilder
            .builder(ItemPitcherPlantStack.class)
            .vanillaItem(VanillaItemId.PITCHER_PLANT)
            .build();
  }
}
