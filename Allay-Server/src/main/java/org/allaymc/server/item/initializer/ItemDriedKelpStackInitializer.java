package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDriedKelpStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDriedKelpStackInitializer {
  static void init() {
    ItemTypes.DRIED_KELP_TYPE = ItemTypeBuilder
            .builder(ItemDriedKelpStack.class)
            .vanillaItem(VanillaItemId.DRIED_KELP)
            .build();
  }
}
