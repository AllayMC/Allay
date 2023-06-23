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
public interface ItemMangroveChestBoat extends ItemStack {
  ItemType<ItemMangroveChestBoat> TYPE = ItemTypeBuilder
          .builder(ItemMangroveChestBoat.class)
          .vanillaItem(VanillaItemId.MANGROVE_CHEST_BOAT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
