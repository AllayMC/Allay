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
public interface ItemMusicDisc11 extends ItemStack {
  ItemType<ItemMusicDisc11> TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc11.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_11, true)
          .addBasicComponents()
          .build().register(ItemTypeRegistry.getRegistry());
}
