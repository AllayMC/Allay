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
public interface ItemRabbitHide extends ItemStack {
  ItemType<ItemRabbitHide> TYPE = ItemTypeBuilder
          .builder(ItemRabbitHide.class)
          .vanillaItem(VanillaItemId.RABBIT_HIDE, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
