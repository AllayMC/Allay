package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBambooRaftStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooRaftStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_RAFT_TYPE = ItemTypeBuilder
            .builder(ItemBambooRaftStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_RAFT)
            .build();
  }
}
