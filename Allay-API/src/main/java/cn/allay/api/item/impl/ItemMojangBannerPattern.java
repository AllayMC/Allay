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
public interface ItemMojangBannerPattern extends ItemStack {
  ItemType<ItemMojangBannerPattern> TYPE = ItemTypeBuilder
          .builder(ItemMojangBannerPattern.class)
          .vanillaItem(VanillaItemId.MOJANG_BANNER_PATTERN, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
