package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLeatherHorseArmorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherHorseArmorStackInitializer {
  static void init() {
    ItemTypes.LEATHER_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemLeatherHorseArmorStack.class)
            .vanillaItem(VanillaItemId.LEATHER_HORSE_ARMOR)
            .build();
  }
}
