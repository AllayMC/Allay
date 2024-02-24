package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSpongeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpongeStackInitializer {
  static void init() {
    ItemTypes.SPONGE_TYPE = ItemTypeBuilder
            .builder(ItemSpongeStack.class)
            .vanillaItem(VanillaItemId.SPONGE)
            .build();
  }
}
