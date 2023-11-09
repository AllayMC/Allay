package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooChestRaftStack extends ItemStack {
  ItemType<ItemBambooChestRaftStack> BAMBOO_CHEST_RAFT_TYPE = ItemTypeBuilder
          .builder(ItemBambooChestRaftStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_CHEST_RAFT)
          .build();
}
