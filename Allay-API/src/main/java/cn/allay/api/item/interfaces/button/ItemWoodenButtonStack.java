package cn.allay.api.item.interfaces.button;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenButtonStack extends ItemStack {
  ItemType<ItemWoodenButtonStack> WOODEN_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemWoodenButtonStack.class)
          .vanillaItem(VanillaItemId.WOODEN_BUTTON)
          .build();
}
