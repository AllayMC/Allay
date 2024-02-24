package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemArmorStandStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmorStandStackInitializer {
  static void init() {
    ItemTypes.ARMOR_STAND_TYPE = ItemTypeBuilder
            .builder(ItemArmorStandStack.class)
            .vanillaItem(VanillaItemId.ARMOR_STAND)
            .build();
  }
}
