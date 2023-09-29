package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherPodStack extends ItemStack {
  ItemType<ItemPitcherPodStack> PITCHER_POD_TYPE = ItemTypeBuilder
          .builder(ItemPitcherPodStack.class)
          .vanillaItem(VanillaItemId.PITCHER_POD)
          .build();
}
