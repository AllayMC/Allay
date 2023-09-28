package cn.allay.api.item.interfaces.musicdisc;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMusicDiscRelicStack extends ItemStack {
  ItemType<ItemMusicDiscRelicStack> MUSIC_DISC_RELIC_TYPE = ItemTypeBuilder
          .builder(ItemMusicDiscRelicStack.class)
          .vanillaItem(VanillaItemId.MUSIC_DISC_RELIC)
          .build();
}
