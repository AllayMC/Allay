package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGunpowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGunpowderStackInitializer {
  static void init() {
    ItemTypes.GUNPOWDER_TYPE = ItemTypeBuilder
            .builder(ItemGunpowderStack.class)
            .vanillaItem(VanillaItemId.GUNPOWDER)
            .build();
  }
}
