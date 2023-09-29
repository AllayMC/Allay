package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedMuttonStack extends ItemStack {
  ItemType<ItemCookedMuttonStack> COOKED_MUTTON_TYPE = ItemTypeBuilder
          .builder(ItemCookedMuttonStack.class)
          .vanillaItem(VanillaItemId.COOKED_MUTTON)
          .build();
}
