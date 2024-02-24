package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPhantomMembraneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPhantomMembraneStackInitializer {
  static void init() {
    ItemTypes.PHANTOM_MEMBRANE_TYPE = ItemTypeBuilder
            .builder(ItemPhantomMembraneStack.class)
            .vanillaItem(VanillaItemId.PHANTOM_MEMBRANE)
            .build();
  }
}
