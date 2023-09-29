package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedBeefStack extends ItemStack {
  ItemType<ItemCookedBeefStack> COOKED_BEEF_TYPE = ItemTypeBuilder
          .builder(ItemCookedBeefStack.class)
          .vanillaItem(VanillaItemId.COOKED_BEEF)
          .build();
}
