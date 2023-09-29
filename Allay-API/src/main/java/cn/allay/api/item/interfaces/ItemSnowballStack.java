package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowballStack extends ItemStack {
  ItemType<ItemSnowballStack> SNOWBALL_TYPE = ItemTypeBuilder
          .builder(ItemSnowballStack.class)
          .vanillaItem(VanillaItemId.SNOWBALL)
          .build();
}
