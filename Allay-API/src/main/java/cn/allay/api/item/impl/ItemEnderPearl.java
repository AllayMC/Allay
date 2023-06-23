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
public interface ItemEnderPearl extends ItemStack {
  ItemType<ItemEnderPearl> TYPE = ItemTypeBuilder
          .builder(ItemEnderPearl.class)
          .vanillaItem(VanillaItemId.ENDER_PEARL, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
