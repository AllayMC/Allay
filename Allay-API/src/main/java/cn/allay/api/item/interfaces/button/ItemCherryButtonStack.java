package cn.allay.api.item.interfaces.button;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryButtonStack extends ItemStack {
  ItemType<ItemCherryButtonStack> CHERRY_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemCherryButtonStack.class)
          .vanillaItem(VanillaItemId.CHERRY_BUTTON)
          .build();
}
