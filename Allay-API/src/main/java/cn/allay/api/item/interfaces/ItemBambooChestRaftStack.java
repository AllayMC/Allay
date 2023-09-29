package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

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
