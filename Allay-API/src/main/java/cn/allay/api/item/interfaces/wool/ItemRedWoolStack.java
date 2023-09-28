package cn.allay.api.item.interfaces.wool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedWoolStack extends ItemStack {
  ItemType<ItemRedWoolStack> RED_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemRedWoolStack.class)
          .vanillaItem(VanillaItemId.RED_WOOL)
          .build();
}
