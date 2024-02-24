package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemAcaciaTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaTrapdoorStackInitializer {
  static void init() {
    ItemTypes.ACACIA_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaTrapdoorStack.class)
            .vanillaItem(VanillaItemId.ACACIA_TRAPDOOR)
            .build();
  }
}
