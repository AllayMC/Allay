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
public interface ItemOxidizedCopper extends ItemStack {
  ItemType<ItemOxidizedCopper> TYPE = ItemTypeBuilder
          .builder(ItemOxidizedCopper.class)
          .vanillaItem(VanillaItemId.OXIDIZED_COPPER, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
