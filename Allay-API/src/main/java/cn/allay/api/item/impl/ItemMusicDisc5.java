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
public interface ItemMusicDisc5 extends ItemStack {
  ItemType<ItemMusicDisc5> TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc5.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_5, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
