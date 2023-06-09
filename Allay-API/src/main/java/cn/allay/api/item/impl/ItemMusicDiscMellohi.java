package cn.allay.api.item.impl;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;
import cn.allay.api.item.type.ItemTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMellohi extends ItemStack {
  ItemType<ItemMusicDiscMellohi> TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscMellohi.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_MELLOHI, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
