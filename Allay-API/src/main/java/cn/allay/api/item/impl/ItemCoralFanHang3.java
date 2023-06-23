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
public interface ItemCoralFanHang3 extends ItemStack {
  ItemType<ItemCoralFanHang3> TYPE = ItemTypeBuilder
          .builder(ItemCoralFanHang3.class)
          .vanillaItem(VanillaItemId.CORAL_FAN_HANG3, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
