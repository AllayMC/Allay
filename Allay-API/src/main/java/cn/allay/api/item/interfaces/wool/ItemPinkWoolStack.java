package cn.allay.api.item.interfaces.wool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkWoolStack extends ItemStack {
  ItemType<ItemPinkWoolStack> PINK_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemPinkWoolStack.class)
          .vanillaItem(VanillaItemId.PINK_WOOL)
          .build();
}
