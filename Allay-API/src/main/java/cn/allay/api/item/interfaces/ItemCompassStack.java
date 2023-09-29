package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCompassStack extends ItemStack {
  ItemType<ItemCompassStack> COMPASS_TYPE = ItemTypeBuilder
          .builder(ItemCompassStack.class)
          .vanillaItem(VanillaItemId.COMPASS)
          .build();
}
