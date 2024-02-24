package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondHorseArmorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHorseArmorStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_HORSE_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemDiamondHorseArmorStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_HORSE_ARMOR)
            .build();
  }
}
