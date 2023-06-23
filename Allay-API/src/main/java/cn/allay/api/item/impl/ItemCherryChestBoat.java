package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryChestBoat extends ItemStack {
  ItemType<ItemCherryChestBoat> TYPE = ItemTypeBuilder
          .builder(ItemCherryChestBoat.class)
          .vanillaItem(VanillaItemId.CHERRY_CHEST_BOAT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
