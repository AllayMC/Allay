package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWaxedWeatheredCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_TRAPDOOR)
            .build();
  }
}
