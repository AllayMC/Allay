package cn.allay.api.item.interfaces.musicdisc;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscMallStack extends ItemStack {
  ItemType<ItemMusicDiscMallStack> MUSIC_DISC_MALL_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscMallStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_MALL)
          .build();
}
