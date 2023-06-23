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
public interface ItemTripWire extends ItemStack {
  ItemType<ItemTripWire> TYPE = ItemTypeBuilder
          .builder(ItemTripWire.class)
          .vanillaItem(VanillaItemId.TRIP_WIRE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
