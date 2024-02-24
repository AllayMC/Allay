package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWaxedOxidizedCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
            .build();
  }
}
