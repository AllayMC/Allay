package cn.allay.api.item.interfaces.musicdisc;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscStalStack extends ItemStack {
  ItemType<ItemMusicDiscStalStack> MUSIC_DISC_STAL_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscStalStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_STAL)
          .build();
}
