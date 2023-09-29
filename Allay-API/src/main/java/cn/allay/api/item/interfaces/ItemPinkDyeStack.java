package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkDyeStack extends ItemStack {
  ItemType<ItemPinkDyeStack> PINK_DYE_TYPE = ItemTypeBuilder
          .builder(ItemPinkDyeStack.class)
          .vanillaItem(VanillaItemId.PINK_DYE)
          .build();
}
