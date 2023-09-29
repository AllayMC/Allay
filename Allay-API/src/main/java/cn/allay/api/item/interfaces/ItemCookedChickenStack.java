package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedChickenStack extends ItemStack {
  ItemType<ItemCookedChickenStack> COOKED_CHICKEN_TYPE = ItemTypeBuilder
          .builder(ItemCookedChickenStack.class)
          .vanillaItem(VanillaItemId.COOKED_CHICKEN)
          .build();
}
