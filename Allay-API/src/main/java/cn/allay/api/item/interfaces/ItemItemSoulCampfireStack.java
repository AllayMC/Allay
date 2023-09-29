package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSoulCampfireStack extends ItemStack {
  ItemType<ItemItemSoulCampfireStack> ITEM_SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
          .builder(ItemItemSoulCampfireStack.class)
          .vanillaItem(VanillaItemId.ITEM_SOUL_CAMPFIRE)
          .build();
}
