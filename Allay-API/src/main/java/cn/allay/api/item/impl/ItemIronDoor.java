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
public interface ItemIronDoor extends ItemStack {
  ItemType<ItemIronDoor> TYPE = ItemTypeBuilder
          .builder(ItemIronDoor.class)
          .vanillaItem(VanillaItemId.IRON_DOOR, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
