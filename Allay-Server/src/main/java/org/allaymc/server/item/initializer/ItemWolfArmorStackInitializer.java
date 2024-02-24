package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWolfArmorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWolfArmorStackInitializer {
  static void init() {
    ItemTypes.WOLF_ARMOR_TYPE = ItemTypeBuilder
            .builder(ItemWolfArmorStack.class)
            .vanillaItem(VanillaItemId.WOLF_ARMOR)
            .build();
  }
}
