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
public interface ItemMusicDiscWait extends ItemStack {
  ItemType<ItemMusicDiscWait> TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscWait.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_WAIT, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
