package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRottenFleshStack extends ItemStack {
  ItemType<ItemRottenFleshStack> ROTTEN_FLESH_TYPE = ItemTypeBuilder
          .builder(ItemRottenFleshStack.class)
          .vanillaItem(VanillaItemId.ROTTEN_FLESH)
          .build();
}
