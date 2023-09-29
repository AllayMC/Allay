package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterlilyStack extends ItemStack {
  ItemType<ItemWaterlilyStack> WATERLILY_TYPE = ItemTypeBuilder
          .builder(ItemWaterlilyStack.class)
          .vanillaItem(VanillaItemId.WATERLILY)
          .build();
}
