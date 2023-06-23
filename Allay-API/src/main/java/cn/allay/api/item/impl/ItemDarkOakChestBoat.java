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
public interface ItemDarkOakChestBoat extends ItemStack {
  ItemType<ItemDarkOakChestBoat> TYPE = ItemTypeBuilder
          .builder(ItemDarkOakChestBoat.class)
          .vanillaItem(VanillaItemId.DARK_OAK_CHEST_BOAT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
