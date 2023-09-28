package cn.allay.api.item.interfaces.button;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonButtonStack extends ItemStack {
  ItemType<ItemCrimsonButtonStack> CRIMSON_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonButtonStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_BUTTON)
          .build();
}
