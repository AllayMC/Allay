package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBrewingStandStack extends ItemStack {
  ItemType<ItemItemBrewingStandStack> ITEM_BREWING_STAND_TYPE = ItemTypeBuilder
          .builder(ItemItemBrewingStandStack.class)
          .vanillaItem(VanillaItemId.ITEM_BREWING_STAND)
          .build();
}
