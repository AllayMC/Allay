package cn.allay.api.item.interfaces.musicdisc;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDisc5Stack extends ItemStack {
  ItemType<ItemMusicDisc5Stack> MUSIC_DISC_5_TYPE = ItemTypeBuilder
          .builder(ItemMusicDisc5Stack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_5)
          .build();
}
