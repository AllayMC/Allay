package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemReserved6Stack extends ItemStack {
  ItemType<ItemReserved6Stack> RESERVED6_TYPE = ItemTypeBuilder
          .builder(ItemReserved6Stack.class)
          .vanillaItem(VanillaItemId.RESERVED6)
          .build();
}
