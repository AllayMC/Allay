package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMushroomStewStack extends ItemStack {
  ItemType<ItemMushroomStewStack> MUSHROOM_STEW_TYPE = ItemTypeBuilder
          .builder(ItemMushroomStewStack.class)
          .vanillaItem(VanillaItemId.MUSHROOM_STEW)
          .build();
}
