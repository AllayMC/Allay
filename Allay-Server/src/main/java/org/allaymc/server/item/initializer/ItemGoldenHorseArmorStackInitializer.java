package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenHorseArmorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHorseArmorStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemGoldenHorseArmorStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_HORSE_ARMOR)
            .build();
  }
}
