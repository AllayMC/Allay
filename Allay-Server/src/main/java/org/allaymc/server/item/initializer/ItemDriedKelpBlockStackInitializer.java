package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDriedKelpBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDriedKelpBlockStackInitializer {
  static void init() {
    ItemTypes.DRIED_KELP_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemDriedKelpBlockStack.class)
            .vanillaItem(VanillaItemId.DRIED_KELP_BLOCK)
            .build();
  }
}
