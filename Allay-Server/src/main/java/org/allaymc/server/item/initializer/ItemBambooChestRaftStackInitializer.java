package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooChestRaftStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooChestRaftStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_CHEST_RAFT_TYPE = ItemTypeBuilder
            .builder(ItemBambooChestRaftStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_CHEST_RAFT)
            .build();
  }
}
