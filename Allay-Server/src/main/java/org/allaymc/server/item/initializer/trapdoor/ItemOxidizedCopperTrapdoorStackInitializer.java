package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemOxidizedCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.OXIDIZED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_COPPER_TRAPDOOR)
            .build();
  }
}
