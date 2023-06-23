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
public interface ItemDeadFireCoral extends ItemStack {
  ItemType<ItemDeadFireCoral> TYPE = ItemTypeBuilder
          .builder(ItemDeadFireCoral.class)
          .vanillaItem(VanillaItemId.DEAD_FIRE_CORAL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
