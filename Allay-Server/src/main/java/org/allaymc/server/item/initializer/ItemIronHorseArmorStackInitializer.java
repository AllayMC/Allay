package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronHorseArmorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHorseArmorStackInitializer {
  static void init() {
    ItemTypes.IRON_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemIronHorseArmorStack.class)
            .vanillaItem(VanillaItemId.IRON_HORSE_ARMOR)
            .build();
  }
}
