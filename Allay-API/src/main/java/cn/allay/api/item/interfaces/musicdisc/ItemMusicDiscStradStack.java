package cn.allay.api.item.interfaces.musicdisc;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscStradStack extends ItemStack {
  ItemType<ItemMusicDiscStradStack> MUSIC_DISC_STRAD_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscStradStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_STRAD)
          .build();
}
